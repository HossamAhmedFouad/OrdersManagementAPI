package com.APIX.order.model;

import com.APIX.Product.model.Product;

import java.util.ArrayList;
import java.util.List;

public class CompoundOrder extends Order{

    private List<Order> orders = new ArrayList<Order>();
    public CompoundOrder(int id, List<Product> products, double shippingFee, List<Order> orders) {
        super(id, products, shippingFee);
        this.orders = orders;
    }
    public List<Order> getOrders() {
        return orders;
    }

    public void addOrder (Order order) {
        orders.add (order);
    }

    public void removeOrder(Order order){
        orders.remove(order);
    }

    public double getTotalPrice() {
        double totalPrice = 0;
        for (Order order : getOrders()) {
            totalPrice += order.getTotalPrice();
        }
        totalPrice += getShippingFee();
        return totalPrice;
    }
    public void printDetails() {
        System.out.println("This order has id " + getId() + " and status " + getStatus() + ".");
        System.out.println("This compound order contains " + getOrders().size() + " simple orders.");
        for (Order order : getOrders()) {
            order.printDetails();
        }
        System.out.println("The shipping fee of this order is " + getShippingFee() + ".");
        System.out.println("The total price of this compound order is " + getTotalPrice() + ".");
    }
}
