package com.APIX.product.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class Product {
    private Long id;
    private String name;
    private String vendor;
    private String Category; //TODO: change string into ENUM
    private double price;
    private int count;

    public Product(
                   @JsonProperty("id") Long id,
                   @JsonProperty("name") String name,
                   @JsonProperty("vendor") String vendor,
                   @JsonProperty("category") String category,
                   @JsonProperty("price") double price,
                   @JsonProperty("count") int count) {
        this.id = id;
        this.name = name;
        this.vendor = vendor;
        Category = category;
        this.price = price;
        this.count = count;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
