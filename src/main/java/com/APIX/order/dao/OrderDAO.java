package com.APIX.order.dao;

import com.APIX.order.model.Order;

import java.util.List;

public interface OrderDAO {
    boolean save(Order order);

    Order getById(int orderId);

    List<Order> getAll();

    void update(Order order);

    void delete(int orderId);
}
