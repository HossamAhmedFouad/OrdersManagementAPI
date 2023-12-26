package com.APIX.notification.service;

import com.APIX.notification.model.Notification;
import com.APIX.order.model.Order;

public abstract class NotificationFactory {
    /**
     * Map ACTION => Template
     * Template => Language
     *
     *
     * Map<Action, List<String>>myMap
     *
     * myMap[ORDER_PLACEMENT] => List<String>orderPlacementTemplates
     * ENG
     *
     */
    abstract Notification createPlacementTemplate(String lang, Order order);
    abstract Notification createShipmentTemplate(String lang);
    abstract Notification createCancellationTemplate(String lang);

}
