package com.APIX.order.service;

import com.APIX.CustomRepository;
import com.APIX.order.Manager.OrderManager;
import com.APIX.order.model.Order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl {
    @Autowired
    private  CustomRepository<Order, Integer> orderDAO;



    public void placeOrder(Order order) {
        OrderManager orderManager=OrderManager.createManager(order,orderDAO);
        orderManager.placeOrder(order);

    }


    public void cancelOrder(Order order) {
        OrderManager orderManager=OrderManager.createManager(order,orderDAO);
        orderManager.cancel(order);
    }


    public Order getOrderById(int orderId) {
        return orderDAO.getById(orderId);
    }


    public List<Order> getAllOrders() {
        return orderDAO.getAll();
    }


    public void updateOrder(Order order) {
        orderDAO.update(order);
    }


    public void deleteOrder(int orderId) {
        orderDAO.delete(orderId);
    }
}