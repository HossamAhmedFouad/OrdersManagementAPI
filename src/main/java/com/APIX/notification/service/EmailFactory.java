package com.APIX.notification.service;

import com.APIX.notification.model.Notification;
import com.APIX.order.model.Order;

import java.util.HashMap;
import java.util.Map;

public class EmailFactory extends NotificationFactory {

    Map<String, String>placementLang = new HashMap<>();
    Map<String, String>shipmentLang = new HashMap<>();
    Map<String, String>cancellationLang = new HashMap<>();

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

        return null;
    }

    @Override
    Notification createShipmentTemplate(String lang) {
        return null;
    }

    @Override
    Notification createCancellationTemplate(String lang) {
        return null;
    }
}
