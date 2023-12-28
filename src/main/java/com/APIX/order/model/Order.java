package com.APIX.order.model;



import com.APIX.product.model.Product;
import com.APIX.user.model.User;

import java.time.LocalDateTime;
import java.util.List;

public abstract class Order {
    private final int id;
    private final List<Product> products;
    private final double shippingFee;
    private final User user;
    private final LocalDateTime orderDateTime;

    private String status;
    public Order(int id, List<Product> products, double shippingFee, User user, LocalDateTime orderDateTime) {


        if (id <= 0 || products == null || shippingFee < 0) {
            throw new IllegalArgumentException("Invalid input parameters for order creation.");
        }

        this.id = id;
        this.products = products;
        this.shippingFee = shippingFee;
        this.user = user;
        this.orderDateTime = orderDateTime;
        this.status = "Placed";
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
        return 4;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public double getTotalPrice() {
        double totalPrice = 0;
        for (Product product : getProducts()) {
            totalPrice += product.getPrice();
        }
        return totalPrice;
    }

}
