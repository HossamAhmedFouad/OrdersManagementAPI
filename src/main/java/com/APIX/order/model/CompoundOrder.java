package com.APIX.order.model;


import com.APIX.product.model.Product;
import com.APIX.user.model.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CompoundOrder extends Order{

    private List<SimpleOrder> orders = new ArrayList<>();
    public CompoundOrder(int id, List<Product> products, double shippingFee, User user, List<SimpleOrder> orders) {
        super(id, products, shippingFee,user);
        this.orders = orders;
    }
    public void addOrder (SimpleOrder order) {
        orders.add (order);
    }
    public List<SimpleOrder> getOrders() {
        return orders;
    }



    public void removeOrder(SimpleOrder order){
        orders.remove(order);
    }

}
