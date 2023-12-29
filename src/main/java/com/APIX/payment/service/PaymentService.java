package com.APIX.payment.service;

import com.APIX.order.model.Order;

public interface PaymentService {
    boolean checkBalance(Long id, double amount);

    boolean payOrder(Long id, double amount);

    boolean refund(Long id, double amount);

//    boolean refund();
}
