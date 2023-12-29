package com.APIX.ObserverPattern;

import com.APIX.ObserverPattern.Observer;
import com.APIX.order.model.Order;

public interface Subject {
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers(String language, Order order);
}
