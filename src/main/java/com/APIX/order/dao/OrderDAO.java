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
public class OrderDAO implements CustomRepository<Order, Long> {
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

        SimpleOrder dummyOrder = new SimpleOrder(dummyProducts,  1L);
        SimpleOrder dummyOrder2 = new SimpleOrder(dummyProducts, 2L);
        SimpleOrder dummyOrder3 = new SimpleOrder(dummyProducts, 3L);
        SimpleOrder dummyOrder4 = new SimpleOrder(dummyProducts, 4L);

        orders.add(dummyOrder);
        List<SimpleOrder>friendsOrders = new ArrayList<>();

        friendsOrders.add(dummyOrder2);
        friendsOrders.add(dummyOrder3);
        friendsOrders.add(dummyOrder4);

        Order dummyCompound = new CompoundOrder( dummyProducts, 1L, friendsOrders);
        orders.add(dummyCompound);
    }

    @Override
    public boolean save(Order order) {
        return orders.add(order);
    }

    @Override
    public Order getById(Long orderId) {
        for (Order order : orders) {
            if (order.getId().equals(orderId)) {
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
        for(int i = 0; i < orders.size(); i++){
            if(orders.get(i).getId().equals(order.getId())){
                order.setOrderDateTime(orders.get(i).getOrderDateTime());
                order.setUserID(orders.get(i).getUserID());
                order.setStatus(orders.get(i).getStatus());
                orders.set(i, order);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean delete(Long orderId) {
        return orders.removeIf(order -> order.getId().equals(orderId));
    }
}
