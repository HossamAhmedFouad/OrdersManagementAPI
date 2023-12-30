package com.APIX.order.model;



import com.APIX.product.model.Product;
import com.APIX.product.model.ProductDTO;
import com.APIX.user.model.User;

import java.time.LocalDateTime;
import java.util.List;

public class SimpleOrder extends Order{
    public SimpleOrder(int id, List<ProductDTO> products, Long userID) {
        super(id, products,userID);
    }

    public void printDetails() {
        System.out.println("This order has id " + getId() + " and status " + getStatus() + ".");
        System.out.println("This order contains " + getProducts().size() + " products.");
        for (ProductDTO product : getProducts()) {
            System.out.println(product.getId() + ": " + product.getQuantity());
        }
        System.out.println("The shipping fee of this order is " + getShippingFee() + ".");
        System.out.println("The total price of this order is " + getTotalPrice() + ".");
    }
}
