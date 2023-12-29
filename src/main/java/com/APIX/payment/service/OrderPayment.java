package com.APIX.payment.service;

import com.APIX.user.model.User;
import com.APIX.user.service.UserService;

public class OrderPayment implements PaymentService{
    @Override
    public boolean checkBalance(Long id, double amount) {
        return UserService.getUserById(id).getBalance() >= amount;
    }

    @Override
    public boolean payOrder(Long id, double amount) {
        if(checkBalance(id, amount)){
            User user = UserService.getUserById(id);
            double balance = user.getBalance();
            balance -= amount;
            user.setBalance(balance);
            UserService.updateUser(id, user);
            return true;
        }
        return false;
    }

    @Override
    public boolean refund(Long id, double amount) {
        User user = UserService.getUserById(id);
        if(user == null) return false;
        user.setBalance(user.getBalance() + amount);
        UserService.updateUser(id, user);
        return true;
    }
}
