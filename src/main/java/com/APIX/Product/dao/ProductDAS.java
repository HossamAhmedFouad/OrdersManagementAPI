package com.APIX.product.dao;

import com.APIX.product.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository("productDAS")
public class ProductDAS implements ProductDAO{

    private static List<Product> db = new ArrayList<>();
    @Override
    public int insertProduct(UUID id, Product product) {
        db.add(new Product(
                id,
                product.getName(),
                product.getVendor(),
                product.getCategory(),
                product.getPrice(),
                product.getCount()));
        return 1;
    }

    @Override
    public List<Product> getProducts() {
        return db;
    }
}
