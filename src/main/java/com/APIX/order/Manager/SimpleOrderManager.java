package com.APIX.order.Manager;
import com.APIX.order.model.Order;
import com.APIX.order.model.OrderState;
import com.APIX.order.service.OrderService;
import com.APIX.payment.service.OrderPayment;
import com.APIX.payment.service.PaymentService;
import com.APIX.product.model.Product;
import com.APIX.product.service.ProductService;

import java.time.Duration;
import java.time.LocalDateTime;

public class SimpleOrderManager extends OrderManager {


    PaymentService paymentService = new OrderPayment();
    @Override
    public boolean placeOrder(Order order) {
        if(paymentService.payOrder(order.getUser().getId(), order.getTotalPrice())){
            for(Product product : order.getProducts()){

                if(!ProductService.decrementProduct(product.getId(), 1)){
                    return false;
                }
            }
            notifyObservers("ENG",order);
            return OrderService.saveOrder(order);
        }
        return false;
    }

    @Override
    public boolean cancel(Order order) {
        if(order.getStatus() == OrderState.PLACED){
            order.setStatus(OrderState.CANCELED);
            OrderService.updateOrder(order);
            paymentService.refund(order.getUser().getId(), order.getTotalPrice());
            //Send notification for cancellation
            notifyObservers("ENG",order);
            return true;
        }

        if(order.getStatus() == OrderState.CANCELED){
            return false;
        }

        if(order.getStatus() == OrderState.SHIPPED){
            Duration duration = Duration.between(LocalDateTime.now(), order.getOrderDateTime());
            long diffInMinutes = duration.toMinutes();
            if(diffInMinutes < 1){
                paymentService.refund(order.getUser().getId(), order.getShippingFee());
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
            if(paymentService.payOrder(order.getUser().getId(), order.getShippingFee())){
                order.setStatus(OrderState.SHIPPED);
                order.setOrderDateTime(LocalDateTime.now());
                OrderService.updateOrder(order);
                notifyObservers("ENG",order);
                return true;
            } else return false;
        }
        return false;
    }
}
