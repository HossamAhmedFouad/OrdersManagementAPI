package com.APIX.order.model;



import com.APIX.product.model.Product;
import com.APIX.user.model.User;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.time.LocalDateTime;
import java.util.List;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = SimpleOrder.class, name = "simpleOrder"),
        @JsonSubTypes.Type(value = CompoundOrder.class, name = "compoundOrder"),
})
public abstract class Order {
    private final int id;
    private final List<Product> products;
    private final double shippingFee;
    private final User user;
    private LocalDateTime orderDateTime;

    private OrderState status;
    public Order(int id, List<Product> products, double shippingFee, User user) {


        if (id <= 0 || products == null || shippingFee < 0) {
            throw new IllegalArgumentException("Invalid input parameters for order creation.");
        }

        this.id = id;
        this.products = products;
        this.shippingFee = shippingFee;
        this.user = user;
        this.orderDateTime = LocalDateTime.now();
        this.status = OrderState.PLACED;
    }
    public User getUser() {
        return user;
    }
    public int getId() {
        return id;
    }
    public List<Product> getProducts() {
        return products;
    }
    public double getShippingFee() {
        return getTotalPrice() / 10;
    }
    public OrderState getStatus() {
        return status;
    }
    public void setStatus(OrderState status) {
        this.status = status;
    }
    public double getTotalPrice() {
        double totalPrice = 0;
        for (Product product : getProducts()) {
            totalPrice += product.getPrice();
        }
        return totalPrice;
    }

    public LocalDateTime getOrderDateTime() {
        return orderDateTime;
    }

    public void setOrderDateTime(LocalDateTime dateTime){
        this.orderDateTime = dateTime;
    }
}
