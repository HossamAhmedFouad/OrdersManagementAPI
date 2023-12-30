package com.APIX.order.Manager;
import com.APIX.notification.Factory.EmailFactory;
import com.APIX.notification.Factory.SMSFactory;
import com.APIX.order.model.Order;
import com.APIX.order.model.OrderState;
import com.APIX.order.model.SimpleOrder;
import com.APIX.order.service.OrderService;
import com.APIX.payment.service.OrderPayment;
import com.APIX.payment.service.PaymentService;
import com.APIX.product.model.Product;
import com.APIX.product.model.ProductDTO;
import com.APIX.product.service.ProductService;
import com.APIX.user.service.UserService;

import java.time.Duration;
import java.time.LocalDateTime;

public class SimpleOrderManager extends OrderManager {


    PaymentService paymentService = new OrderPayment();

    SimpleOrderManager(){
        this.notificationObservers.add(new SMSFactory());
        this.notificationObservers.add(new EmailFactory());
    }

    @Override
    public boolean placeOrder(Order order) {
        if(paymentService.payOrder(order.getUserID(), order.getTotalPrice())){
            for(ProductDTO product : order.getProducts()){

                if(!ProductService.decrementProduct(product.getId(), product.getQuantity())){
                    return false;
                }
            }
            changeOrderStatus(order, OrderState.PLACED);
            order.printDetails();
            return OrderService.saveOrder(order);
        }
        return false;
    }

    @Override
    public boolean cancel(Order order) {
        if(order.getStatus() == OrderState.PLACED){
            System.out.println("cancel placement order");
            paymentService.refund(order.getUserID(), order.getTotalPrice());
            //Send notification for cancellation
            changeOrderStatus(order, OrderState.CANCELED);
//            OrderService.updateOrder(order);
            OrderService.deleteOrder(order.getId());
            return true;
        }

        if(order.getStatus() == OrderState.CANCELED){
            return false;
        }

        if(order.getStatus() == OrderState.SHIPPED){
            System.out.println("cancel shipping order");
            Duration duration = Duration.between(order.getOrderDateTime(), LocalDateTime.now());
            System.out.println("duration: " + duration);
            long diffInMinutes = duration.toSeconds();
            System.out.println("diffInMinutes: " + diffInMinutes);
            if(diffInMinutes < 5){
                paymentService.refund(order.getUserID(), order.getShippingFee());
                changeOrderStatus(order, OrderState.CANCELEDSHIPPING);
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
            System.out.println("ship order");
            if(paymentService.payOrder(order.getUserID(), order.getShippingFee())){
                changeOrderStatus(order, OrderState.SHIPPED);
                order.setOrderDateTime(LocalDateTime.now());

                OrderService.updateOrder(order);
                return true;
            } else return false;
        }
        return false;
    }
}
