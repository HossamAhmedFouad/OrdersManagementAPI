package com.APIX.notification.service;

import com.APIX.order.model.OrderState;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NotificationStat {
    public static Map<String,Integer>emailFreq=new HashMap<>();
    public static Map<String,Integer>phoneFreq=new HashMap<>();
    public static Map<OrderState,Integer>templateFreq=new HashMap<>();

    public static void addEmail(String email){
        int valueOrDefault = emailFreq.getOrDefault(email, 0);
        emailFreq.put(email,valueOrDefault+1);
    }
    public static void addPhone(String phone){
        int valueOrDefault = phoneFreq.getOrDefault(phone, 0);
        phoneFreq.put(phone,valueOrDefault+1);
    }

    public static void addTemp(OrderState orderState){
        int valueOrDefault = templateFreq.getOrDefault(orderState, 0);
        templateFreq.put(orderState,valueOrDefault+1);
    }
    public StatDTO getMostNotified(){
        int maxValue=-1;
        String maxEntry=null;
        for (Map.Entry<String, Integer> entry : emailFreq.entrySet()) {
            if (entry.getValue() > maxValue) {
                maxValue = entry.getValue();
                maxEntry = entry.getKey();
            }
        }
        for (Map.Entry<String, Integer> entry : phoneFreq.entrySet()) {
            if (entry.getValue() > maxValue) {
                maxValue = entry.getValue();
                maxEntry = entry.getKey();
            }
        }
        maxValue=-1;
        OrderState maxTemp=null;
        for (Map.Entry<OrderState, Integer> entry : templateFreq.entrySet()) {
            if (entry.getValue() > maxValue) {
                maxValue = entry.getValue();
                maxTemp = entry.getKey();
            }
        }

        return new StatDTO(maxEntry,maxTemp);

    }


}
