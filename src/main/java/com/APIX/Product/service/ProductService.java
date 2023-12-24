package com.APIX.orders.service;

import com.APIX.orders.dao.ProductDAO;
import com.APIX.orders.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private  ProductDAO productDAO;
    public int addProduct(Product product){
        return productDAO.insertProduct(product);
    }

    public List<Product> getProducts(){
        return productDAO.getProducts();
    }
}
