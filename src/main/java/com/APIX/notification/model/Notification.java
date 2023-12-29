package com.APIX.notification.model;

import com.APIX.order.model.OrderState;
import com.APIX.user.model.Language;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.time.LocalDateTime;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = EmailNotification.class, name = "EmailNotification"),
        @JsonSubTypes.Type(value = SMSNotification.class, name = "SMSNotification"),
})
public class Notification {

    static Long nextID = 1L;
    Long id;
    Language notificationLanguage;
    String notificationText;
    OrderState notificationType;
    LocalDateTime dateTime;


    public Notification(Language notificationLanguage, String notificationText, OrderState notificationType) {
        this.id = nextID++;
        this.notificationLanguage = notificationLanguage;
        this.notificationText = notificationText;
        this.notificationType = notificationType;
        this.dateTime = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Language getNotificationLanguage() {
        return notificationLanguage;
    }

    public void setNotificationLanguage(Language notificationLanguage) {
        this.notificationLanguage = notificationLanguage;
    }

    public String getNotificationText() {
        return notificationText;
    }

    public void setNotificationText(String notificationText) {
        this.notificationText = notificationText;
    }

    public OrderState getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(OrderState notificationType) {
        this.notificationType = notificationType;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

}
