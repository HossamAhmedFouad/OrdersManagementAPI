package com.APIX.order.Manager;

import com.APIX.order.model.CompoundOrder;
import com.APIX.order.model.Order;
import com.APIX.order.model.OrderState;
import com.APIX.order.model.SimpleOrder;
import com.APIX.order.service.OrderService;
import com.APIX.payment.service.OrderPayment;
import com.APIX.payment.service.PaymentService;
import com.APIX.product.model.Product;
import com.APIX.product.service.ProductService;
import com.APIX.user.model.User;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public class CompoundOrderManager extends OrderManager {
    PaymentService paymentService = new OrderPayment();

    @Override
    public boolean placeOrder(Order order) {
        CompoundOrder compoundOrder = (CompoundOrder) order;

        if(paymentService.payOrder(compoundOrder.getUser().getId(), compoundOrder.getTotalPrice())){
            //TODO: change product object to productDTO
            for(Product product : compoundOrder.getProducts()){
                if(!ProductService.decrementProduct(product.getId(), 1)){
                    return false;
                }
            }
        }else return false;

        for(Order o : compoundOrder.getOrders()){
            if(paymentService.payOrder(o.getUser().getId(), o.getTotalPrice())){
                for(Product product : o.getProducts()){
                    if(!ProductService.decrementProduct(product.getId(), 1)){
                        return false;
                    }
                }
            } else return false;
        }

        return OrderService.saveOrder(order);
    }


    @Override
    public boolean cancel(Order order) {

        if(order.getStatus() == OrderState.CANCELED){
            return false;
        }

        CompoundOrder compoundOrder = (CompoundOrder) order;

        if(order.getStatus() == OrderState.PLACED){
            //1 - Refund for Compound Order Owner and return products to stock
            paymentService.refund(order.getUser().getId(), order.getTotalPrice());
            for(Product product : compoundOrder.getProducts()){
                ProductService.incrementProduct(product.getId(), 1);
            }

            //2 - Refund for all other users in compound order and return their products to stock
            for(SimpleOrder o : compoundOrder.getOrders()){
                paymentService.refund(o.getUser().getId(), o.getTotalPrice());
                for(Product product : o.getProducts()){
                    ProductService.incrementProduct(product.getId(), 1);
                }
            }

            order.setStatus(OrderState.CANCELED);
            OrderService.updateOrder(order);

            return true;
        }


        if(order.getStatus() == OrderState.SHIPPED){

            Duration duration = Duration.between(LocalDateTime.now(), order.getOrderDateTime());
            long diffInMinutes = duration.toMinutes();
            if(diffInMinutes < 1){
                //1 - Refund Shipping Fee for Compound Order Owner
                paymentService.refund(compoundOrder.getUser().getId(), compoundOrder.getShippingFee());

                //2 - Refund Shipping Fee for other users in current compound order
                for(SimpleOrder o : compoundOrder.getOrders()){
                    paymentService.refund(o.getUser().getId(), compoundOrder.getShippingFee());
                }

                order.setStatus(OrderState.PLACED);
                OrderService.updateOrder(order);
                return true;
            }
            return false;
        }
        return false;
    }

    @Override
    public boolean shipOrder(Order order) {
        if(order.getStatus() == OrderState.PLACED){
            CompoundOrder compoundOrder = (CompoundOrder) order;
            if(!paymentService.payOrder(compoundOrder.getUser().getId(), compoundOrder.getShippingFee())) return false;
            for(SimpleOrder simpleOrder : compoundOrder.getOrders()){
                if(!paymentService.payOrder(simpleOrder.getUser().getId(), compoundOrder.getShippingFee())) return false;
            }
            compoundOrder.setStatus(OrderState.SHIPPED);
            compoundOrder.setOrderDateTime(LocalDateTime.now());
            OrderService.updateOrder(compoundOrder);
        }
        return false;
    }
}
