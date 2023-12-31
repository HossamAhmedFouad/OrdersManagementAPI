package com.APIX.order.Manager;
import com.APIX.notification.Factory.EmailFactory;
import com.APIX.notification.Factory.SMSFactory;
import com.APIX.order.model.Order;
import com.APIX.order.model.OrderState;
import com.APIX.order.service.OrderService;
import com.APIX.payment.service.OrderPayment;
<<<<<<< Updated upstream
import com.APIX.product.model.ProductDTO;
import com.APIX.product.service.ProductService;

=======
import com.APIX.payment.service.PaymentService;
import com.APIX.product.model.ProductDTO;
import com.APIX.product.service.ProductService;
>>>>>>> Stashed changes
import java.time.Duration;
import java.time.LocalDateTime;

public class SimpleOrderManager extends OrderManager {
    public SimpleOrderManager(){
        this.paymentService = new OrderPayment();
        addObserver(new SMSFactory());
        addObserver(new EmailFactory());
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

        if(order.getStatus() == OrderState.PLACED || order.getStatus() == OrderState.READY){
            if(paymentService.refund(order.getUserID(), order.getTotalPrice())){
                for(ProductDTO product : order.getProducts()){
                    if(!ProductService.incrementProduct(product.getId(), product.getQuantity())) return false;
                }
                //Send notification for cancellation
                changeOrderStatus(order, OrderState.CANCELED);
                OrderService.deleteOrder(order.getId());
                return true;
            }
        }

        if(order.getStatus() == OrderState.SHIPPING){
            Duration duration = Duration.between(order.getOrderDateTime(), LocalDateTime.now());
            long diffInSeconds = duration.toSeconds();
            if(diffInSeconds < 10){
                if(paymentService.refund(order.getUserID(), order.getShippingFee())){
                    changeOrderStatus(order, OrderState.READY);
                    OrderService.updateOrder(order);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean shipOrder(Order order) {
        if(order.getStatus() == OrderState.READY || order.getStatus() == OrderState.PLACED){
            if(paymentService.payOrder(order.getUserID(), order.getShippingFee())){
                changeOrderStatus(order, OrderState.SHIPPING);
                order.setOrderDateTime(LocalDateTime.now());
                OrderService.updateOrder(order);
                return true;

            } else return false;
        }
        return false;
    }
}
