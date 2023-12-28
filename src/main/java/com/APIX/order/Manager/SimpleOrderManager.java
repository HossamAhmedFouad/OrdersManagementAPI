package com.APIX.order.Manager;

import com.APIX.CustomRepository;
import com.APIX.order.model.Order;
import com.APIX.order.service.OrderService;
import com.APIX.payment.service.OrderPayment;
import com.APIX.payment.service.PaymentService;
import com.APIX.product.model.Product;
import com.APIX.product.service.ProductService;

import java.time.Duration;
import java.time.LocalDateTime;

public class SimpleOrderManager extends OrderManager {


    @Override
    public boolean placeOrder(Order order) {
        PaymentService paymentService = new OrderPayment();
        if(paymentService.payOrder(order.getUser().getId(), order.getTotalPrice(), order.getShippingFee())){
            for(Product product : order.getProducts()){

                if(!ProductService.decrementProduct(product.getId(), 1)){
                    return false;
                }
            }

            return true;
        }
        return false;
    }

    @Override
    public boolean cancel(Order order) {
//        Duration duration = Duration.between(LocalDateTime.now(), order.getOrderDateTime());
//        long diffInMinutes = duration.toMinutes();
//        if(diffInMinutes > 1) return false;
        return false;
    }
}
