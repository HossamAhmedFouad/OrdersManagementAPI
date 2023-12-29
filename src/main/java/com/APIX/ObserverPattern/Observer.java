package com.APIX.ObserverPattern;

import com.APIX.order.model.Order;

public interface Observer {
    void update(String language, Order order);
}
