//package com.APIX.payment.service;
//
//import com.APIX.order.model.CompoundOrder;
//import com.APIX.order.model.Order;
//import com.APIX.order.model.SimpleOrder;
//import com.APIX.user.model.User;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class PaymentServiceImpl implements PaymentService{
//    @Override
//    public boolean checkBalance(Order order) {
//
//        if (order instanceof SimpleOrder) {
//            SimpleOrder simpleOrder = (SimpleOrder) order;
//            User user = simpleOrder.getUser();
//            double balance = user.getBalance();
//            double totalPrice = simpleOrder.getTotalPrice();
//            return balance >= totalPrice;
//        }
//
//        else if (order instanceof CompoundOrder) {
//            CompoundOrder compoundOrder = (CompoundOrder) order;
//            List<Order> orders = compoundOrder.getOrders();
//            for (Order o : orders) {
//                double shippingfee = compoundOrder.getShippingFee();
//                shippingfee /= orders.size();
//
//                SimpleOrder simpleOrder = (SimpleOrder) o;
//                User user = simpleOrder.getUser();
//                double balance = user.getBalance();
//                double totalPrice = simpleOrder.getTotalPrice() + shippingfee;
//                return balance >= totalPrice;
//
//            }
//            return true;
//        }
//
//        else {
//            return false;
//        }
//    }
//
//    @Override
//    public void payOrder(Order order) {
//        if (order instanceof SimpleOrder) {
//            SimpleOrder simpleOrder = (SimpleOrder) order;
//            User user = simpleOrder.getUser();
//            double balance = user.getBalance();
//            double totalPrice = simpleOrder.getTotalPrice();
//            balance -= totalPrice;
//            user.setBalance(balance);
//
//        }
//        else if (order instanceof CompoundOrder) {
//            CompoundOrder compoundOrder = (CompoundOrder) order;
//            double shippingfee = compoundOrder.getShippingFee();
//
//            List<Order> orders = compoundOrder.getOrders();
//            shippingfee /= orders.size();
//            for (Order o : orders) {
//                SimpleOrder simpleOrder = (SimpleOrder) o;
//                User user = simpleOrder.getUser();
//                double balance = user.getBalance();
//                double totalPrice = simpleOrder.getTotalPrice();
//                balance -= totalPrice - shippingfee;
//                user.setBalance(balance);
//            }
//        }
//    }
//}
