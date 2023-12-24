package com.APIX.order.model;

import java.util.List;

public interface Order {
    public double getTotalPrice();
//    public double getShippingFees(ShippingStrategy strategy);
    public void addOrder(Order order);
    public void removeOrder(Order order);
    public List<Order> getOrders();
}
