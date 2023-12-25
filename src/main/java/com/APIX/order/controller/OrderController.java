package com.APIX.order.controller;

import com.APIX.order.model.CompoundOrder;
import com.APIX.order.model.Order;
import com.APIX.order.service.OrderService;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    private final OrderService orderService;


    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addOrder(Order order){
        boolean res = orderService.placeOrder(order);
        if (!res) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("cannot add order");
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @PostMapping("/compound")
    public ResponseEntity<String> addCompoundOrder( @RequestBody CompoundOrder compoundOrder){
        boolean res = orderService.placeCompoundOrder(compoundOrder);
        if (res) {
            return ResponseEntity.status(HttpStatus.CREATED).body(compoundOrder.toString());
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Cannot create compound order");
        }
    }
    @GetMapping("/{orderId}")
    public Order getOrderById(@PathVariable int orderId) {
        return orderService.getOrderById(orderId);
    }

    @GetMapping("/all")
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @PutMapping("/update")
    public void updateOrder(@RequestBody Order order) {
        orderService.updateOrder(order);
    }

    @DeleteMapping("/delete/{orderId}")
    public void deleteOrder(@PathVariable int orderId) {
        orderService.deleteOrder(orderId);
    }
}
