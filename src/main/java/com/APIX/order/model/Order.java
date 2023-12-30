package com.APIX.order.model;



import com.APIX.product.model.Product;
import com.APIX.product.model.ProductDTO;
import com.APIX.product.service.ProductService;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.time.LocalDateTime;
import java.util.List;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = SimpleOrder.class, name = "simpleOrder"),
        @JsonSubTypes.Type(value = CompoundOrder.class, name = "compoundOrder"),
})
public abstract class Order {
    private static Long nextID = 1L;
    private Long id;
    private final List<ProductDTO> products;
    private double shippingFee;
    private Long userID;
    private LocalDateTime orderDateTime;

    private OrderState status;
    public Order(List<ProductDTO> products, Long userID) {

        if (products == null) {
            throw new IllegalArgumentException("Invalid input parameters for order creation.");
        }
        this.id = nextID++;
        this.products = products;
        this.userID = userID;
        this.orderDateTime = LocalDateTime.now();
        this.status = OrderState.PLACED;
    }
    public Long getUserID() {
        return userID;
    }
    public void setUserID(Long userID){
        this.userID = userID;
    }
    public Long getId() {
        return id;
    }
    public void setID(Long id){this.id = id;}
    public List<ProductDTO> getProducts() {
        return products;
    }

    public double getShippingFee() {
        this.shippingFee = getTotalPrice() / 10;
        return shippingFee;
    }
    public OrderState getStatus() {
        return status;
    }
    public void setStatus(OrderState status) {
        this.status = status;
    }
    public double getTotalPrice() {
        double totalPrice = 0;
        for (ProductDTO lineItem : getProducts()) {
            Long productId = lineItem.getId();
            int quantity = lineItem.getQuantity();

            Product product = ProductService.getProduct(productId);
            double productPrice = product.getPrice();
            totalPrice += productPrice * quantity;
        }
        return totalPrice;
    }

    public LocalDateTime getOrderDateTime() {
        return orderDateTime;
    }

    public void setOrderDateTime(LocalDateTime dateTime){
        this.orderDateTime = dateTime;
    }
    public abstract void printDetails();
}
