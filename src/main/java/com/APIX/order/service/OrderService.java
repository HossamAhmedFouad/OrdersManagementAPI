package com.APIX.order.service;

import com.APIX.CustomRepository;
import com.APIX.order.Manager.OrderManager;
import com.APIX.order.model.Order;
import com.APIX.order.model.OrderState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
//    @Autowired
    private static CustomRepository<Order, Integer> orderDAO;
    @Autowired
    public OrderService(CustomRepository<Order, Integer> orderDAO){
        OrderService.orderDAO = orderDAO;
    }

    public static boolean placeOrder(Order order) {
        OrderManager orderManager = OrderManager.createManager(order);
        return orderManager.placeOrder(order);
    }


    public static boolean cancelOrder(Order order) {
        OrderManager orderManager = OrderManager.createManager(order);
        return orderManager.cancel(order);
    }

    public static boolean shipOrder(Order order){
        OrderManager orderManager = OrderManager.createManager(order);
        return orderManager.shipOrder(order);
    }

    ///////////////////////////////////DataBase Access Functions///////////////////////////////////////////////////
    public static boolean saveOrder(Order order){
        return orderDAO.save(order);
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