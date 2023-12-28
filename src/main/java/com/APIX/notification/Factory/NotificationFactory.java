package com.APIX.notification.Factory;

import com.APIX.notification.model.Notification;
import com.APIX.order.model.Order;

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

}
