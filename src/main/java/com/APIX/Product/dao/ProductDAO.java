package com.APIX.product.dao;

import com.APIX.product.model.Product;

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
