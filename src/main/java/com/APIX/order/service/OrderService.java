package com.APIX.order.service;

import com.APIX.order.model.Order;

import java.util.List;

public interface OrderService {
    void placeOrder(Order order);

    Order getOrderById(int orderId);

    List<Order> getAllOrders();

    void updateOrder(Order order);

    void deleteOrder(int orderId);
}
