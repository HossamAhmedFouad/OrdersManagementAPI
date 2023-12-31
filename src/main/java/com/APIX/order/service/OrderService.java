package com.APIX.order.service;

import com.APIX.CustomRepository;
import com.APIX.order.Factory.ManagerFactory;
import com.APIX.order.Manager.OrderManager;
import com.APIX.order.model.Order;
import com.APIX.order.model.OrderState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {
    private static CustomRepository<Order, Long> orderDAO;
    @Autowired
    public OrderService(CustomRepository<Order, Long> orderDAO){
        OrderService.orderDAO = orderDAO;
    }

    public static boolean placeOrder(Order order) {
        OrderManager orderManager = ManagerFactory.createManager(order);
        return orderManager.placeOrder(order);
    }


    public static boolean cancelOrder(Order order) {
        OrderManager orderManager = ManagerFactory.createManager(order);
        return orderManager.cancel(order);
    }

    public static boolean shipOrder(Order order){
        OrderManager orderManager = ManagerFactory.createManager(order);
        return orderManager.shipOrder(order);
    }

    @Scheduled(fixedDelay = 10000)
    private void shipOrders(){
        List<Order> order = OrderService.getAllOrders();
        for(Order o : order){
            if(o.getStatus() == OrderState.SHIPPING){
                Duration duration = Duration.between(o.getOrderDateTime(), LocalDateTime.now());
                long diffInSeconds = duration.toSeconds();
                if(diffInSeconds >= 10) o.setStatus(OrderState.SHIPPED);
            }
        }
    }

    ///////////////////////////////////DataBase Access Functions///////////////////////////////////////////////////
    public static boolean saveOrder(Order order){
        return orderDAO.save(order);
    }

    public static Order getOrderById(Long orderId) {
        return orderDAO.getById(orderId);
    }


    public static List<Order> getAllOrders() {
        return orderDAO.getAll();
    }


    public static boolean updateOrder(Order order) {
        return orderDAO.update(order);
    }


    public static boolean deleteOrder(Long orderId) {
        return orderDAO.delete(orderId);
    }
}