package com.APIX.order.Manager;

import com.APIX.notification.Factory.EmailFactory;
import com.APIX.notification.Factory.SMSFactory;
import com.APIX.order.model.CompoundOrder;
import com.APIX.order.model.Order;
import com.APIX.order.model.OrderState;
import com.APIX.order.model.SimpleOrder;
import com.APIX.order.service.OrderService;
import com.APIX.payment.service.OrderPayment;
import com.APIX.payment.service.PaymentService;
import com.APIX.product.model.Product;
import com.APIX.product.model.ProductDTO;
import com.APIX.product.service.ProductService;
import org.springframework.cglib.core.Local;

import java.time.Duration;
import java.time.LocalDateTime;


public class CompoundOrderManager extends OrderManager {
    PaymentService paymentService = new OrderPayment();

    CompoundOrderManager(){
        this.notificationObservers.add(new SMSFactory());
        this.notificationObservers.add(new EmailFactory());
    }

    @Override
    public boolean placeOrder(Order order) {
        CompoundOrder compoundOrder = (CompoundOrder) order;

        if(paymentService.payOrder(compoundOrder.getUserID(), compoundOrder.getTotalPrice())){
            for(ProductDTO product : compoundOrder.getProducts()){
                if(!ProductService.decrementProduct(product.getId(), product.getQuantity())){
                    return false;
                }
            }
        }else return false;

        changeOrderStatus(compoundOrder, OrderState.PLACED);
        for(Order o : compoundOrder.getOrders()){
            if(paymentService.payOrder(o.getUserID(), o.getTotalPrice())){
                for(ProductDTO product : o.getProducts()){
                    if(!ProductService.decrementProduct(product.getId(), product.getQuantity())){
                        return false;
                    }
                }
                changeOrderStatus(o, OrderState.PLACED);

            } else return false;
        }

        return OrderService.saveOrder(compoundOrder);
    }


    @Override
    public boolean cancel(Order order) {

        if(order.getStatus() == OrderState.CANCELED){
            return false;
        }

        CompoundOrder compoundOrder = (CompoundOrder) order;

        if(order.getStatus() == OrderState.PLACED){
            //1 - Refund for Compound Order Owner and return products to stock
            paymentService.refund(order.getUserID(), order.getTotalPrice());
            changeOrderStatus(compoundOrder, OrderState.CANCELED);
            for(ProductDTO product : compoundOrder.getProducts()){
                ProductService.incrementProduct(product.getId(), product.getQuantity());
            }

            //2 - Refund for all other users in compound order and return their products to stock
            for(SimpleOrder o : compoundOrder.getOrders()){
                paymentService.refund(o.getUserID(), o.getTotalPrice());
                for(ProductDTO product : o.getProducts()){
                    ProductService.incrementProduct(product.getId(), product.getQuantity());
                }
                changeOrderStatus(o, OrderState.CANCELED);
            }

            OrderService.updateOrder(compoundOrder);

            return true;
        }


        if(order.getStatus() == OrderState.SHIPPED){

            Duration duration = Duration.between(order.getOrderDateTime(), LocalDateTime.now());
            long diffInMinutes = duration.toMinutes();
            if(diffInMinutes < 1){
                //1 - Refund Shipping Fee for Compound Order Owner
                paymentService.refund(compoundOrder.getUserID(), compoundOrder.getShippingFee());
                //2 - Refund Shipping Fee for other users in current compound order
                for(SimpleOrder o : compoundOrder.getOrders()){
                    paymentService.refund(o.getUserID(), compoundOrder.getShippingFee());
                }
                changeOrderStatus(compoundOrder, OrderState.CANCELED);
                for(SimpleOrder o : compoundOrder.getOrders()){
                    changeOrderStatus(o, OrderState.CANCELED);
                }
                OrderService.updateOrder(compoundOrder);
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
            if(!paymentService.payOrder(compoundOrder.getUserID(), compoundOrder.getShippingFee())) return false;
            for(SimpleOrder simpleOrder : compoundOrder.getOrders()){
                if(!paymentService.payOrder(simpleOrder.getUserID(), compoundOrder.getShippingFee())) return false;
                changeOrderStatus(simpleOrder, OrderState.SHIPPED);
            }
            changeOrderStatus(compoundOrder, OrderState.SHIPPED);
            compoundOrder.setOrderDateTime(LocalDateTime.now());
            OrderService.updateOrder(compoundOrder);
            return true;
        }
        return false;
    }
}
