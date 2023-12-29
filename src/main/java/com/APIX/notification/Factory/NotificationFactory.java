package com.APIX.notification.Factory;

import com.APIX.notification.model.Notification;
import com.APIX.notification.service.NotificationService;
import com.APIX.order.model.Order;
import com.APIX.order.model.OrderState;
import com.APIX.user.model.Language;

import java.util.HashMap;
import java.util.Map;

public abstract class NotificationFactory {

    Map<Language, String> placementLang = new HashMap<>();
    Map<Language, String>shipmentLang = new HashMap<>();
    Map<Language, String>cancellationLang = new HashMap<>();
    String notificationText;

    abstract Notification createPlacementTemplate(Language lang, Order order);
    abstract Notification createShipmentTemplate(Language lang, Order order);
    abstract Notification createCancellationTemplate(Language lang, Order order);
    public static String fillPlaceholders(String template, Object... args) {
        return String.format(template, args);
    }
    public boolean notify(Language language, Order order) {
        if(order.getStatus()== OrderState.PLACED){
            Notification notification=createPlacementTemplate(language,order);
            return NotificationService.addNotificationToQueue(notification);

        }
        if(order.getStatus()== OrderState.CANCELED) {
            Notification notification = createCancellationTemplate(language, order);
            return NotificationService.addNotificationToQueue(notification);
        }
        if(order.getStatus()== OrderState.SHIPPED){
            Notification notification=createShipmentTemplate(language,order);
            return NotificationService.addNotificationToQueue(notification);
        }
        return false;

    }


}
