package com.APIX.order.dao;

import com.APIX.CustomRepository;
import com.APIX.order.model.CompoundOrder;
import com.APIX.order.model.Order;
import com.APIX.order.model.SimpleOrder;
import com.APIX.product.dao.ProductDAO;
import com.APIX.product.model.Product;
import com.APIX.product.model.ProductDTO;
import com.APIX.product.service.ProductService;
import com.APIX.user.model.User;
import com.APIX.user.service.UserService;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("OrderDAO")
public class OrderDAO implements CustomRepository<Order, Integer> {
    private static final List<Order> orders = new ArrayList<>();

    static {
        initDummyDataOrder();
    }

    private static void initDummyDataOrder() {
        ProductDTO p1 = new ProductDTO(1L,2);
        ProductDTO p2 = new ProductDTO(2L,3);

        List<ProductDTO> dummyProducts = new ArrayList<>();
        dummyProducts.add(p1);
        dummyProducts.add(p2);

        SimpleOrder dummyOrder = new SimpleOrder(1, dummyProducts,  1L);
        SimpleOrder dummyOrder2 = new SimpleOrder(2, dummyProducts, 2L);
        SimpleOrder dummyOrder3 = new SimpleOrder(3, dummyProducts, 3L);
        SimpleOrder dummyOrder4 = new SimpleOrder(4, dummyProducts, 4L);

        orders.add(dummyOrder);
        List<SimpleOrder>friendsOrders = new ArrayList<>();

        friendsOrders.add(dummyOrder2);
        friendsOrders.add(dummyOrder3);
        friendsOrders.add(dummyOrder4);

        Order dummyCompound = new CompoundOrder(5, dummyProducts, 1L, friendsOrders);
        orders.add(dummyCompound);
    }

    @Override
    public boolean save(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("Order cannot be null.");
        }
        if (order.getTotalPrice() > UserService.getUserById(order.getUserID()).getBalance()) {
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
