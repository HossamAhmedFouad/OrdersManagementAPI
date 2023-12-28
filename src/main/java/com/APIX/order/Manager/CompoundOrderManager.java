package com.APIX.order.Manager;

import com.APIX.CustomRepository;
import com.APIX.order.model.CompoundOrder;
import com.APIX.order.model.Order;
import com.APIX.payment.service.OrderPayment;
import com.APIX.payment.service.PaymentService;
import com.APIX.product.model.Product;
import com.APIX.product.service.ProductService;
import com.APIX.user.model.User;

import java.util.List;

public class CompoundOrderManager extends OrderManager {
    @Override
    public boolean placeOrder(Order order) {
        CompoundOrder compoundOrder = (CompoundOrder) order;

        PaymentService paymentService = new OrderPayment();

        User orderOwner = compoundOrder.getUser();
        List<Product> ownerProducts = compoundOrder.getProducts();
        double ownerTotalPrice = 0;
        for(Product product : ownerProducts){
            ownerTotalPrice += product.getPrice();
        }

        double shippingFee = compoundOrder.getShippingFee() / (compoundOrder.getOrders().size() + 1);


        if(paymentService.payOrder(orderOwner.getId(), ownerTotalPrice, shippingFee)){
            for(Product product : ownerProducts){
                if(!ProductService.decrementProduct(product.getId(), 1)){
                    return false;
                }
            }
        }else return false;

        for(Order o : compoundOrder.getOrders()){
            if(paymentService.payOrder(o.getUser().getId(), o.getTotalPrice(), shippingFee)){
                for(Product product : o.getProducts()){
                    if(!ProductService.decrementProduct(product.getId(), 1)){
                        return false;
                    }
                }
            } else return false;
        }

        return true;
    }


    @Override
    public boolean cancel(Order order) {
        return false;
    }
}
