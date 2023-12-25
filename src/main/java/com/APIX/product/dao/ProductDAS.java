package com.APIX.product.dao;

import com.APIX.product.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository("productDAS")
public class ProductDAS implements com.APIX.product.dao.ProductDAO {

    private static List<Product> db = new ArrayList<>();
    @Override
    public boolean insertProduct(Product product) {
        if(product.getPrice() < 0 || product.getCount() < 0 ){
            return false;
        }

        UUID id = UUID.randomUUID();
        db.add(new Product(
                id,
                product.getName(),
                product.getVendor(),
                product.getCategory(),
                product.getPrice(),
                product.getCount()));
        return true;
    }

    @Override
    public List<Product> getProducts() {
        return db;
    }

    @Override
    public Product getProduct(UUID id) {
        for(Product product : db){
            if(product.getSerialNumber().equals(id)){
                return product;
            }
        }
        return null;
    }

    @Override
    public boolean deleteProduct(UUID id) {
        for(int i = 0; i < db.size(); i++){
            if(db.get(i).getSerialNumber().equals(id)){
                db.remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean updateProduct(UUID id, Product product) {
        if(product.getPrice() < 0 || product.getCount() < 0 ){
            return false;
        }

        for (int i = 0; i < db.size(); i++) {
            if (db.get(i).getSerialNumber().equals(id)) {
                db.set(i, product);
                db.get(i).setSerialNumber(id);
                return true;
            }
        }
        return false;
    }
}
