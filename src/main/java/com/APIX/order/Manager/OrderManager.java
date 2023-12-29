package com.APIX.order.Manager;
import com.APIX.ObserverPattern.Observer;
import com.APIX.notification.Factory.NotificationFactory;
import com.APIX.order.model.Order;
import com.APIX.order.model.OrderState;
import com.APIX.order.model.SimpleOrder;
import com.APIX.user.model.Language;
import com.APIX.user.service.UserService;

import java.util.ArrayList;
import java.util.List;

public abstract class OrderManager {
    protected List<NotificationFactory>notificationObservers=new ArrayList<>();
    public static OrderManager createManager(Order order){

        if(order instanceof SimpleOrder){
            return new SimpleOrderManager();
        }else{
            return new CompoundOrderManager();
        }

    }
    public abstract boolean placeOrder(Order order);
    public abstract boolean cancel(Order order);
    public abstract boolean shipOrder(Order order);

    void addObserver(NotificationFactory observer){
        notificationObservers.add(observer);
    };
    void removeObserver(Observer observer){
        notificationObservers.remove(observer);
    };
    void notifyObservers(Language language, Order order){
        for(NotificationFactory obs:notificationObservers){
            obs.notify(language,order);
        }
    };
    void setNotificationObservers(List<NotificationFactory>observers){
        this.notificationObservers=observers;
    }

    protected void changeOrderStatus(Order order, OrderState newState){
        order.setStatus(newState);
        notifyObservers(UserService.getUserById(order.getUserID()).getLanguage(), order);
    }







}
