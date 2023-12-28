package com.APIX.order.service;

import com.APIX.CustomRepository;
import com.APIX.order.dao.OrderDAOImpl;
import com.APIX.order.model.CompoundOrder;
import com.APIX.order.model.Order;
import com.APIX.order.model.SimpleOrder;
import com.APIX.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public abstract class OrderManager {
    protected CustomRepository<Order, Integer> orderDAO;
    public static OrderManager createManager(Order order, CustomRepository orderDAO){
        if(order instanceof SimpleOrder){
            return new SimpleOrderManager(orderDAO);
        }else if(order instanceof CompoundOrder){
            return new CompoundOrderManager(orderDAO);
        }
        return null;
    }
    public abstract boolean placeOrder(Order order);
    public abstract boolean cancel(Order order);
    protected boolean validate(Order order){
        User user=order.getUser();
        if(user.getBalance()>=order.getTotalPrice()+order.getShippingFee()){
            return true;
        }
        return true;
    };
    protected void notify(Order order){
        return;
    };







}
