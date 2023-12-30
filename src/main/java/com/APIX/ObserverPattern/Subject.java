package com.APIX.ObserverPattern;

import com.APIX.ObserverPattern.Observer;
import com.APIX.order.model.Order;
import com.APIX.user.model.Language;

public interface Subject {
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers(Language language, Order order);
}
