package com.APIX.payment.service;

import com.APIX.order.model.Order;

public interface PaymentService {
    boolean checkBalance(Order order);

    void payOrder(Order order);
}
