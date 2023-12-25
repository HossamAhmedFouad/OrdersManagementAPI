package com.APIX.order.dao;

import com.APIX.order.model.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderDAOImpl implements OrderDAO{
    private List<Order> orders = new ArrayList<>();

    @Override
    public void save(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("Order cannot be null.");
        }
        orders.add(order);
    }

    @Override
    public Order getById(int orderId) {
        for (Order order : orders) {
            if (order.getId() == orderId) {
                return order;
            }
        }
        throw new IllegalArgumentException("Order with ID " + orderId + " not found.");
    }

    @Override
    public List<Order> getAll() {
        return new ArrayList<>(orders);
    }

    @Override
    public void update(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("Order cannot be null.");
        }

        boolean found = false;
        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).getId() == order.getId()) {
                orders.set(i, order);
                found = true;
                break;
            }
        }

        if (!found) {
            throw new IllegalArgumentException("Order with ID " + order.getId() + " not found.");
        }
    }

    @Override
    public void delete(int orderId) {
        if (orders.removeIf(order -> order.getId() == orderId)) {
        } else {
            throw new IllegalArgumentException("Order with ID " + orderId + " not found.");
        }
    }

}
