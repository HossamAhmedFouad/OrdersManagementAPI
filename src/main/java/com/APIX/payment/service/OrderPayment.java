package com.APIX.payment.service;

import com.APIX.user.model.User;
import com.APIX.user.service.UserService;

public class OrderPayment implements PaymentService{
    @Override
    public boolean checkBalance(Long id, double totalPrice, double shippingFee) {
        return UserService.getUserById(id).getBalance() >= totalPrice + shippingFee;
    }

    @Override
    public boolean payOrder(Long id, double totalPrice, double shippingFee) {
        if(checkBalance(id, totalPrice, shippingFee)){
            User user = UserService.getUserById(id);
            double balance = user.getBalance();
            balance -= (totalPrice + shippingFee);
            user.setBalance(balance);
            UserService.updateUser(id, user);
            return true;
        }
        return false;
    }
}
