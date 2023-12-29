package com.APIX.notification.Factory;

import com.APIX.notification.model.Notification;
import com.APIX.order.model.Order;
import com.APIX.order.model.OrderState;
import com.APIX.user.model.Language;
import com.APIX.user.service.UserService;

import java.time.LocalDateTime;
public class EmailFactory extends NotificationFactory {

    public EmailFactory(){
        placementLang.put(Language.ENG, "Dear %s, your order #%d has been processed on %s");
        placementLang.put(Language.AR, "عزيزي %s، تم معالجة طلبك رقم #%d في تاريخ %s.");

        shipmentLang.put(Language.ENG, "Dear %s, your order #%d has been shipped on %s.");
        shipmentLang.put(Language.AR, "عزيزي %s، تم شحن طلبك رقم #%d في تاريخ %s.");

        cancellationLang.put(Language.ENG, "Dear %s, your order #%d has been canceled as of %s.");
        cancellationLang.put(Language.AR, "عزيزي %s، تم إلغاء طلبك رقم #%d اعتبارًا من %s.");

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
