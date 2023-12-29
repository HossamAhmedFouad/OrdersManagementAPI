package com.APIX.notification.Factory;

import com.APIX.notification.model.Notification;
import com.APIX.notification.service.NotificationService;
import com.APIX.order.model.Order;
import com.APIX.order.model.OrderState;

import java.util.HashMap;
import java.util.Map;

public abstract class NotificationFactory {

    Map<String, String> placementLang = new HashMap<>();
    Map<String, String>shipmentLang = new HashMap<>();
    Map<String, String>cancellationLang = new HashMap<>();
    String notificationText;

    abstract Notification createPlacementTemplate(String lang, Order order);
    abstract Notification createShipmentTemplate(String lang, Order order);
    abstract Notification createCancellationTemplate(String lang, Order order);
    public static String fillPlaceholders(String template, Object... args) {
        return String.format(template, args);
    }
    public boolean notify(String language, Order order) {
        if(order.getStatus()== OrderState.PLACED){
            Notification notification=createPlacementTemplate(language,order);
            return NotificationService.addNotification(notification);

        }
        if(order.getStatus()== OrderState.CANCELED) {
            Notification notification = createCancellationTemplate(language, order);
            return NotificationService.addNotification(notification);
        }
        if(order.getStatus()== OrderState.SHIPPED){
            Notification notification=createShipmentTemplate(language,order);
            return NotificationService.addNotification(notification);
        }
        return false;

    }


}
