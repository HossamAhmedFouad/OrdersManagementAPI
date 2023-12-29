package com.APIX.notification.Factory;

import com.APIX.notification.model.Notification;
import com.APIX.order.model.Order;
import com.APIX.order.model.OrderState;
import com.APIX.user.model.Language;
import com.APIX.user.service.UserService;

import java.time.LocalDateTime;

public class SMSFactory extends NotificationFactory {

    public SMSFactory(){
        placementLang.put(Language.ENG, "Dear %s, your order #%d has been placed on %s.");
        placementLang.put(Language.AR, "عزيزي %s، تم تقديم طلبك #%d في %s.");

        shipmentLang.put(Language.ENG, "Hello %s, your order #%d has been shipped on %s");
        shipmentLang.put(Language.AR, "مرحبًا %s، تم شحن طلبك #%d في %s.");

        cancellationLang.put(Language.ENG, "Hi %s, your order #%d has been canceled on %s.");
        cancellationLang.put(Language.AR, "مرحبًا %s، تم إلغاء طلبك #%d في %s.");
    }

    @Override
    Notification createPlacementTemplate(Language lang, Order order) {
        notificationText = placementLang.get(lang);
        notificationText = fillPlaceholders(notificationText, UserService.getUserById(order.getUserID()).getUsername(), order.getId(), LocalDateTime.now());
        return new Notification(lang, notificationText, OrderState.PLACED);
    }

    @Override
    Notification createShipmentTemplate(Language lang, Order order) {
        notificationText = shipmentLang.get(lang);
        notificationText = fillPlaceholders(notificationText, UserService.getUserById(order.getUserID()).getUsername(), order.getId(), LocalDateTime.now());
        return new Notification(lang, notificationText, OrderState.SHIPPED);
    }

    @Override
    Notification createCancellationTemplate(Language lang, Order order) {
        notificationText = cancellationLang.get(lang);
        notificationText = fillPlaceholders(notificationText, UserService.getUserById(order.getUserID()).getUsername(), order.getId(), LocalDateTime.now());
        return new Notification(lang, notificationText, OrderState.CANCELED);
    }



}
