package com.APIX.product.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductDTO {
    private Long id;
    private int quantity;

    public ProductDTO(
            @JsonProperty("id") Long id,
            @JsonProperty("quantity") int quantity) {
        this.id = id;
        this.quantity = quantity;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
