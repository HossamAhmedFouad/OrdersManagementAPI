package com.APIX.notification.service;

import com.APIX.notification.model.Notification;
import com.APIX.order.model.Order;

import java.time.LocalDateTime;
public class EmailFactory extends NotificationFactory {

    EmailFactory(){
        placementLang.put("ENG", "Dear %s, your order #%d has been processed on %s");
        placementLang.put("AR", "عزيزي %s، تم معالجة طلبك رقم #%d في تاريخ %s.");

        shipmentLang.put("ENG", "Dear %s, your order #%d has been shipped on %s.");
        shipmentLang.put("AR", "عزيزي %s، تم شحن طلبك رقم #%d في تاريخ %s.");

        cancellationLang.put("ENG", "Dear %s, your order #%d has been canceled as of %s.");
        cancellationLang.put("AR", "عزيزي %s، تم إلغاء طلبك رقم #%d اعتبارًا من %s.");

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
