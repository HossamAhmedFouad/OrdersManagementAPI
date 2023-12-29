package com.APIX.notification.model;

import com.APIX.order.model.OrderState;
import com.APIX.user.model.Language;

public class SMSNotification extends Notification{
    String phoneNumber;

    public SMSNotification(Language notificationLanguage, String notificationText, OrderState notificationType,String phoneNumber) {
        super(notificationLanguage, notificationText, notificationType);
        this.phoneNumber=phoneNumber;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
