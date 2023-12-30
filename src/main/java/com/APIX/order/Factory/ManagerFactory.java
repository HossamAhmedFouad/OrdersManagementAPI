package com.APIX.order.Factory;

import com.APIX.order.Manager.CompoundOrderManager;
import com.APIX.order.Manager.OrderManager;
import com.APIX.order.Manager.SimpleOrderManager;
import com.APIX.order.model.Order;
import com.APIX.order.model.SimpleOrder;

public class ManagerFactory {
    public static OrderManager createManager(Order order){
        if(order instanceof SimpleOrder){
            return new SimpleOrderManager();
        }else{
            return new CompoundOrderManager();
        }
    }
}

