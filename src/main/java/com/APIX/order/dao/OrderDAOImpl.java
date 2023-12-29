package com.APIX.order.dao;

import com.APIX.CustomRepository;
import com.APIX.order.model.CompoundOrder;
import com.APIX.order.model.Order;
import com.APIX.order.model.SimpleOrder;
import com.APIX.product.model.Product;
import com.APIX.product.service.ProductService;
import com.APIX.user.model.User;
import com.APIX.user.service.UserService;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("OrderDAOImpl")
public class OrderDAOImpl implements CustomRepository<Order, Integer> {
    private static final List<Order> orders = new ArrayList<>();

    static {
        initDummyDataOrder();
    }

    private static void initDummyDataOrder() {


        Product p1 = new Product(1L, "Dummy Product 1", "Vendor 1", "Electronics", 99.99, 1);
        Product p2 = new Product(2L, "Dummy Product 2", "Vendor 2", "Clothing", 49.99, 2);

        List<Product> dummyProducts = new ArrayList<>();
        dummyProducts.add(p1);
        dummyProducts.add(p2);

        // Create a dummy user
        User user1 = new User("john", "john@gmail.com", "123456789", 1000, "helwan");

        // Create a dummy order
        Order dummyOrder = new SimpleOrder(1, dummyProducts, 5.0, user1);

        orders.add(dummyOrder);
        Order dummyCompound = new CompoundOrder(2, dummyProducts, 5.0, user1, new ArrayList<>());
        orders.add(dummyCompound);
    }

    @Override
    public boolean save(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("Order cannot be null.");
        }
        if (order.getTotalPrice() > order.getUser().getBalance()) {
            throw new IllegalArgumentException("You have not enough balance");
        }
        return orders.add(order);
    }

    @Override
    public Order getById(Integer orderId) {
        for (Order order : orders) {
            if (order.getId() == orderId) {
                return order;
            }
        }
        return null;
    }

    @Override
    public List<Order> getAll() {
        return orders;
    }

    @Override
    public boolean update(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("Order cannot be null.");
        }

        int index = orders.indexOf(order);
        if (index >= 0) {
            orders.set(index, order);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean delete(Integer orderId) {
        return orders.removeIf(order -> order.getId() == orderId);
    }
}
