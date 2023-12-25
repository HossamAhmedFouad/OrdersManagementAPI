package com.APIX.order.service;

import com.APIX.order.dao.OrderDAO;
import com.APIX.order.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private final OrderDAO orderDAO;

    public OrderServiceImpl(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }

    @Override
    public void placeOrder(Order order) {
        orderDAO.save(order);
    }

    @Override
    public Order getOrderById(int orderId) {
        return orderDAO.getById(orderId);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderDAO.getAll();
    }

    @Override
    public void updateOrder(Order order) {
        orderDAO.update(order);
    }

    @Override
    public void deleteOrder(int orderId) {
        orderDAO.delete(orderId);
    }
}