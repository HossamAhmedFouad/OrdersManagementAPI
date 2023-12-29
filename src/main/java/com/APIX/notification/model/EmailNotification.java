package com.APIX.notification.model;

import com.APIX.order.model.OrderState;
import com.APIX.user.model.Language;

public class EmailNotification extends Notification{

    String email;
    public EmailNotification(Language notificationLanguage, String notificationText, OrderState notificationType,String email) {
        super(notificationLanguage, notificationText, notificationType);
        this.email =email;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
