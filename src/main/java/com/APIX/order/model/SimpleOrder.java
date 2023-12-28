package com.APIX.order.model;



import com.APIX.product.model.Product;
import com.APIX.user.model.User;

import java.time.LocalDateTime;
import java.util.List;

public class SimpleOrder extends Order{
    public SimpleOrder(int id, List<Product> products, double shippingFee, User user) {
        super(id, products, shippingFee,user);
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
