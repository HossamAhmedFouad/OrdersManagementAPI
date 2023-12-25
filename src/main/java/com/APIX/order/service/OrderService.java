package com.APIX.order.service;

import com.APIX.order.model.CompoundOrder;
import com.APIX.order.model.Order;

import java.util.List;

public interface OrderService {
    boolean placeOrder(Order order);
    public boolean placeCompoundOrder(CompoundOrder compoundOrder);
    Order getOrderById(int orderId);

    List<Order> getAllOrders();

    void updateOrder(Order order);

    void deleteOrder(int orderId);
}
