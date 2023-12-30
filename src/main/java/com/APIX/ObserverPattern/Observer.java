package com.APIX.ObserverPattern;

import com.APIX.order.model.Order;
import com.APIX.user.model.Language;

public interface Observer {
    void update(Language language, Order order);
}
