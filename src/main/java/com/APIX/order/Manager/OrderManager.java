package com.APIX.order.Manager;

import com.APIX.CustomRepository;
import com.APIX.order.model.CompoundOrder;
import com.APIX.order.model.Order;
import com.APIX.order.model.SimpleOrder;
import com.APIX.order.service.OrderService;
import com.APIX.product.model.Product;
import com.APIX.product.service.ProductService;
import com.APIX.user.model.User;

public abstract class OrderManager {
    public static OrderManager createManager(Order order){

        if(order instanceof SimpleOrder){
            return new SimpleOrderManager();
        }else if(order instanceof CompoundOrder){
            return new CompoundOrderManager();
        }
        return null;
    }
    public abstract boolean placeOrder(Order order);
    public abstract boolean cancel(Order order);

    protected void notify(Order order){
        //send notifications
        //1 -> Email
        //2 -> SMS
    };







}
