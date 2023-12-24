package com.APIX.product.service;

import com.APIX.product.dao.ProductDAO;
import com.APIX.product.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductService {
    @Autowired
    private  ProductDAO productDAO;
    public boolean addProduct(Product product){
        return productDAO.insertProduct(product);
    }

    public List<Product> getProducts(){
        return productDAO.getProducts();
    }

    public Product getProduct(UUID id){
        return productDAO.getProduct(id);
    }

    public boolean deleteProduct(UUID id){
        return productDAO.deleteProduct(id);
    }

    public boolean updateProduct(UUID id, Product product){
        return productDAO.updateProduct(id, product);
    }
}
