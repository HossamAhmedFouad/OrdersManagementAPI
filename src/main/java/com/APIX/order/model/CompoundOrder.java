package com.APIX.order.model;


import com.APIX.product.model.Product;
import com.APIX.product.model.ProductDTO;
import com.APIX.user.model.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CompoundOrder extends Order{

    private List<SimpleOrder> orders = new ArrayList<>();
    public CompoundOrder(int id, List<ProductDTO> products, Long userID, List<SimpleOrder> orders) {
        super(id, products, userID);
        this.orders = orders;
    }
    public void addOrder (SimpleOrder order) {
        orders.add (order);
    }

    public List<SimpleOrder> getOrders() {
        return orders;
    }

    @Override
    public double getShippingFee(){
        return super.getShippingFee() / (orders.size() + 1);
    }

    @Override
    public void printDetails() {

    }

    public void removeOrder(SimpleOrder order){
        orders.remove(order);
    }

}
