package com.APIX.order.service;

import com.APIX.CustomRepository;
import com.APIX.order.Manager.OrderManager;
import com.APIX.order.model.Order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private static CustomRepository<Order, Integer> orderDAO;



    public static void placeOrder(Order order) {
        OrderManager orderManager= OrderManager.createManager(order);
        orderManager.placeOrder(order);

    }


    public static void cancelOrder(Order order) {
        OrderManager orderManager=OrderManager.createManager(order);
        orderManager.cancel(order);
    }


    public static Order getOrderById(int orderId) {
        return orderDAO.getById(orderId);
    }


    public static List<Order> getAllOrders() {
        return orderDAO.getAll();
    }


    public static void updateOrder(Order order) {
        orderDAO.update(order);
    }


    public static void deleteOrder(int orderId) {
        orderDAO.delete(orderId);
    }
}