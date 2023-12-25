package com.APIX.product.dao;

import com.APIX.product.model.Product;

import java.util.List;
import java.util.UUID;

public interface ProductDAO {
    boolean insertProduct(Product product);
    List<Product> getProducts();
    Product getProduct(UUID id);
    boolean deleteProduct(UUID id);
    boolean updateProduct(UUID id, Product product);

}
