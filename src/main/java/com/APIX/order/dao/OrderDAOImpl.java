package com.APIX.order.dao;

import com.APIX.CustomRepository;
import com.APIX.order.model.Order;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("OrderDAOImpl")
public class OrderDAOImpl implements CustomRepository<Order, Integer> {
    private final List<Order> orders = new ArrayList<>();

    @Override
    public boolean save(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("Order cannot be null.");
        }
        if (order.getTotalPrice() > order.getUser().getBalance()) {
            throw new IllegalArgumentException("You have not enough balance");
        }
        return orders.add(order);
    }

    @Override
    public Order getById(Integer orderId) {
        for (Order order : orders) {
            if (order.getId() == orderId) {
                return order;
            }
        }
        return null;
    }

    @Override
    public List<Order> getAll() {
        return orders;
    }

    @Override
    public boolean update(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("Order cannot be null.");
        }

        int index = orders.indexOf(order);
        if (index >= 0) {
            orders.set(index, order);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean delete(Integer orderId) {
        return orders.removeIf(order -> order.getId() == orderId);
    }
}
