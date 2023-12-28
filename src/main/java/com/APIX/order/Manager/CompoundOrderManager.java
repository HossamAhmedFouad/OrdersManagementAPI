package com.APIX.order.Manager;

import com.APIX.CustomRepository;
import com.APIX.order.model.Order;

public class CompoundOrderManager extends OrderManager {
    public CompoundOrderManager(CustomRepository orderDAO) {
        this.orderDAO=orderDAO;
    }

    @Override
    public boolean placeOrder(Order order) {
        return false;
    }

    @Override
    public boolean cancel(Order order) {
        return false;
    }
}
