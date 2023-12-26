package com.APIX.order.service;

import com.APIX.CustomRepository;
import com.APIX.order.model.CompoundOrder;
import com.APIX.order.model.Order;
import com.APIX.payment.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private final CustomRepository<Order, Integer> orderDAO;
    private final PaymentService paymentService;

    @Autowired
    public OrderServiceImpl(CustomRepository<Order, Integer> orderDAO, PaymentService paymentService) {
        this.orderDAO = orderDAO;
        this.paymentService = paymentService;
    }

    @Override
    public boolean placeOrder(Order order) {
        if (paymentService.checkBalance(order)) {
            if (orderDAO.save(order)) {
                System.out.println("Saved order successfully\n");
                paymentService.payOrder(order);
                System.out.println("Paid order successfully\n");
                return true;
            }
        } else {
            System.out.println("Insufficient balance to pay for the order\n");
        }
        return false;
    }
    @Override
    public boolean placeCompoundOrder(CompoundOrder compoundOrder) {
        // Check if the users have enough balance to pay for the suborders
        for (Order order : compoundOrder.getOrders()) {
            if (!paymentService.checkBalance(order)) {
                System.out.println("Insufficient balance to pay for the suborder " + order.getId());
                return false;
            }
        }
        if (orderDAO.save(compoundOrder) ) {
            System.out.println("Saved compound order successfully");
            for (Order order : compoundOrder.getOrders()) {
                paymentService.payOrder(order);
                System.out.println("Paid suborder " + order.getId() + " successfully");
            }
            return true;
        } else {
            System.out.println("Cannot save compound order");
            return false;
        }
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