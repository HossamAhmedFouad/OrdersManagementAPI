package com.APIX.orders.dao;

import com.APIX.orders.model.Product;

import java.util.List;
import java.util.UUID;

public interface ProductDAO {
    int insertProduct(UUID id, Product product);
    List<Product> getProducts();
    default int insertProduct(Product product){
        UUID id = UUID.randomUUID();
        return insertProduct(id, product);
    }
}
