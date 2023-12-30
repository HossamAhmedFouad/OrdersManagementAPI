package com.APIX.order.controller;

import com.APIX.order.Manager.OrderManager;
import com.APIX.order.model.Order;
import com.APIX.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController{
    private OrderManager orderManager;

    @PostMapping("/add")
    public ResponseEntity<String> addOrder(@RequestBody Order order){
        orderManager = OrderManager.createManager(order);
        if(orderManager.placeOrder(order)){
            return ResponseEntity.status(HttpStatus.OK).body("Order Has Been Placed Successfully");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Order Placing Failed");
    }


    @PutMapping("/cancel/{id}")
    public ResponseEntity<String> cancelOrder(@PathVariable("id") int orderID){
        Order order = OrderService.getOrderById(orderID);
        orderManager = OrderManager.createManager(order);
        if(orderManager.cancel(order)){
            return ResponseEntity.status(HttpStatus.OK).body("Order Has Been Cancelled Successfully");
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Order Cancellation Failed");
        }
    }

    @PutMapping("/ship/{id}")
    public ResponseEntity<String> shipOrder(@PathVariable("id") int orderID){
        Order order = OrderService.getOrderById(orderID);
        orderManager = OrderManager.createManager(order);
        if(orderManager.shipOrder(order)){
            return ResponseEntity.status(HttpStatus.OK).body("Order Has Been Shipped Successfully");
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Order Shipping Failed");
        }
    }


    @GetMapping("/{orderId}")
    public Order getOrderById(@PathVariable("orderId") int orderId) {
        return OrderService.getOrderById(orderId);
    }

    @GetMapping("/all")
    public List<Order> getAllOrders() {
        return OrderService.getAllOrders();
    }

    @PutMapping("/update")
    public void updateOrder(@RequestBody Order order) {
        OrderService.updateOrder(order);
    }
    @DeleteMapping("/delete/{orderId}")
    public void deleteOrder(@PathVariable int orderId) {
        ResponseEntity.status(HttpStatus.BAD_REQUEST).body("order cancelled successfully");
    }
}
