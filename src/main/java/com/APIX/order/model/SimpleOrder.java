package com.APIX.order.model;



import com.APIX.product.model.Product;

import java.time.LocalDateTime;
import java.util.List;

public class SimpleOrder extends Order{
    public SimpleOrder(int id, List<Product> products, double shippingFee, LocalDateTime orderDateTime) {
        super(id, products, shippingFee,orderDateTime);
    }
    public double getTotalPrice() {
        double totalPrice = 0;
        for (Product product : getProducts()) {
            totalPrice += product.getPrice();
        }
        totalPrice += getShippingFee();
        return totalPrice;
    }
    public void printDetails() {
        System.out.println("This order has id " + getId() + " and status " + getStatus() + ".");
        System.out.println("This order contains " + getProducts().size() + " products.");
        for (Product product : getProducts()) {
            System.out.println(product.getName() + ": " + product.getPrice());
        }
        System.out.println("The shipping fee of this order is " + getShippingFee() + ".");
        System.out.println("The total price of this order is " + getTotalPrice() + ".");
    }
}
