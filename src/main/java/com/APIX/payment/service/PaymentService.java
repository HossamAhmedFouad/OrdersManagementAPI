package com.APIX.payment.service;

import com.APIX.order.model.Order;

public interface PaymentService {
    boolean checkBalance(Long id, double totalPrice, double shippingFee);

    boolean payOrder(Long id, double totalPrice, double shippingFee);

//    boolean refund();
}
