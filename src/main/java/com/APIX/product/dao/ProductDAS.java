package com.APIX.product.dao;

import com.APIX.CustomRepository;
import com.APIX.product.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository("productDAS")
public class ProductDAS implements CustomRepository<Product, Long> {
    private static List<Product> db = new ArrayList<>();
    @Override
    public Product getById(Long id) {
        for(Product product : db){
            if(product.getId().equals(id)){
                return product;
            }
        }
        return null;
    }

    @Override
    public boolean save(Product product) {
        if(product.getPrice() < 0 || product.getCount() < 0 ){
            return false;
        }

        UUID id = UUID.randomUUID();
        db.add(new Product(
                product.getName(),
                product.getVendor(),
                product.getCategory(),
                product.getPrice(),
                product.getCount()));
        return true;
    }

    @Override
    public List<Product> getAll() {
        return db;
    }

    @Override
    public boolean update(Product product) {
        if(product.getPrice() < 0 || product.getCount() < 0 ){
            return false;
        }

        for (int i = 0; i < db.size(); i++) {
            if (db.get(i).getId().equals(product.getId())) {
                db.set(i, product);
                db.get(i).setId(product.getId());
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean delete(Long id) {
        for(int i = 0; i < db.size(); i++){
            if(db.get(i).getId().equals(id)){
                db.remove(i);
                return true;
            }
        }
        return false;
    }
}
