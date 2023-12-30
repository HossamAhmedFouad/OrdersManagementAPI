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

    @PostMapping
    public ResponseEntity<String> addOrder(@RequestBody Order order){
        orderManager = OrderManager.createManager(order);
        if(orderManager.placeOrder(order)){
            return ResponseEntity.status(HttpStatus.OK).body("Order Has Been Placed Successfully");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Order Placing Failed");
    }

    @PutMapping("/cancel/{id}")
    public ResponseEntity<String> cancelOrder(@PathVariable("id") Long orderID){
        Order order = OrderService.getOrderById(orderID);
        if(order == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid Order ID");
        orderManager = OrderManager.createManager(order);
        if(orderManager.cancel(order)){
            return ResponseEntity.status(HttpStatus.OK).body("Order Has Been Cancelled Successfully");
        } else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Order Cancellation Failed");
        }
    }

    @PutMapping("/ship/{id}")
    public ResponseEntity<String> shipOrder(@PathVariable("id") Long orderID){
        Order order = OrderService.getOrderById(orderID);
        if(order == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid Order ID");
        orderManager = OrderManager.createManager(order);
        if(orderManager.shipOrder(order)){
            return ResponseEntity.status(HttpStatus.OK).body("Order Has Been Shipped Successfully");
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Order Shipping Failed");
        }
    }


    @GetMapping( path = "{orderId}")
    public ResponseEntity<?> getOrderById(@PathVariable("orderId") Long orderId) {
        Order order = OrderService.getOrderById(orderId);
        if(order == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid Order ID");
        return ResponseEntity.status(HttpStatus.OK).body(order);
    }

    @GetMapping
    public List<Order> getAllOrders() {
        return OrderService.getAllOrders();
    }

    @PutMapping(path = "{orderId}")
    public ResponseEntity<String> updateOrder(@PathVariable("orderId") Long orderId, @RequestBody Order order) {
        if(OrderService.getOrderById(orderId) == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid Order ID");
        order.setID(orderId);
        if(OrderService.updateOrder(order)){
            return ResponseEntity.status(HttpStatus.OK).body("Order has been updated successfully");
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Order Updating Failed");
        }
    }
    @DeleteMapping( path = "{orderId}")
    public ResponseEntity<String> deleteOrder(@PathVariable Long orderId) {
        if(OrderService.deleteOrder(orderId)){
            return ResponseEntity.status(HttpStatus.OK).body("Order has been deleted successfully");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order deletion failed");
        }
    }
}
