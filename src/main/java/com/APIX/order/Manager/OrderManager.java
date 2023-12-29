package com.APIX.order.Manager;
import com.APIX.ObserverPattern.Observer;
import com.APIX.notification.Factory.NotificationFactory;
import com.APIX.order.model.Order;
import com.APIX.order.model.SimpleOrder;
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

//    protected void notify(Order order){
//        //send notifications
//        //1 -> Email
//        //2 -> SMS
//    };
    void addObserver(NotificationFactory observer){
        notificationObservers.add(observer);
    };
    void removeObserver(Observer observer){
        notificationObservers.remove(observer);
    };
    void notifyObservers(String language, Order order){
        for(NotificationFactory obs:notificationObservers){
            obs.notify(language,order);
        }
    };
    void setNotificationObservers(List<NotificationFactory>observers){
        this.notificationObservers=observers;
    }







}
