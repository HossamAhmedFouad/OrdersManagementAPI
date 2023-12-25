package com.APIX.order.model;

import com.APIX.Product.model.Product;

import java.util.List;

public abstract class Order {
    private final int id;
    private final List<Product> products;
    private final double shippingFee;
    private String status;
    public Order(int id, List<Product> products, double shippingFee) {
        this.id = id;
        this.products = products;
        this.shippingFee = shippingFee;
        // Set the status to "Placed" by default
        this.status = "Placed";
    }
    public int getId() {
        return id;
    }
    public List<Product> getProducts() {
        return products;
    }
    public double getShippingFee() {
        return shippingFee;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public abstract double getTotalPrice();
    public abstract void printDetails();

}
