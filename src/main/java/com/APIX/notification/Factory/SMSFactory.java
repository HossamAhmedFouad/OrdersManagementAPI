package com.APIX.notification.Factory;

import com.APIX.notification.Factory.NotificationFactory;
import com.APIX.notification.model.Notification;
import com.APIX.order.model.Order;

import java.time.LocalDateTime;

public class SMSFactory extends NotificationFactory {

    SMSFactory(){
        placementLang.put("ENG", "Dear %s, your order #%d has been placed on %s.");
        placementLang.put("AR", "عزيزي %s، تم تقديم طلبك #%d في %s.");

        shipmentLang.put("ENG", "Hello %s, your order #%d has been shipped on %s");
        shipmentLang.put("AR", "مرحبًا %s، تم شحن طلبك #%d في %s.");

        cancellationLang.put("ENG", "Hi %s, your order #%d has been canceled on %s.");
        cancellationLang.put("AR", "مرحبًا %s، تم إلغاء طلبك #%d في %s.");
    }

    @Override
    Notification createPlacementTemplate(String lang, Order order) {
        notificationText = placementLang.get(lang);
        notificationText = fillPlaceholders(notificationText, order.getUser().getUsername(), order.getId(), LocalDateTime.now());
        return new Notification(lang, notificationText, "PLACEMENT");
    }

    @Override
    Notification createShipmentTemplate(String lang, Order order) {
        notificationText = shipmentLang.get(lang);
        notificationText = fillPlaceholders(notificationText, order.getUser().getUsername(), order.getId(), LocalDateTime.now());
        return new Notification(lang, notificationText, "SHIPMENT");
    }

    @Override
    Notification createCancellationTemplate(String lang, Order order) {
        notificationText = cancellationLang.get(lang);
        notificationText = fillPlaceholders(notificationText, order.getUser().getUsername(), order.getId(), LocalDateTime.now());
        return new Notification(lang, notificationText, "CANCELLATION");
    }
}
