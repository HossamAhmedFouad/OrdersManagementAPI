package com.APIX.notification.service;

import com.APIX.notification.model.Notification;

public class SMSFactory extends NotificationFactory{
    @Override
    Notification createPlacementTemplate(String lang) {
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
