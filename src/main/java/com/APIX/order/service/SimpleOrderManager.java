package com.APIX.order.service;

import com.APIX.CustomRepository;
import com.APIX.order.dao.OrderDAOImpl;
import com.APIX.order.model.Order;
import com.APIX.product.model.Product;

public class SimpleOrderManager extends OrderManager {
    public SimpleOrderManager(CustomRepository orderDAO) {
        this.orderDAO=orderDAO;
    }

    @Override
    public boolean placeOrder(Order order) {
        if(validate(order)){
            for (Product product:order.getProducts()){
                //reduce the count of the products from the database
            }
            //reduce the balance
            orderDAO.save(order);
            return true;
        }
        return false;
    }

    @Override
    public boolean cancel(Order order) {
        //step 1 recover the count of the product
        //step 2 recover the money to there friends
        // step3 change the status of the order
            return false;
    }
}
