package com.APIX.notification.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;

public class Notification {

    String notificationLanguage;
    String notificationText;
    String notificationType; //TODO: implement Enum Classes instead of strings for Type and language

    LocalDateTime dateTime;
    List<String> params;


    public Notification(String notificationLanguage, String notificationText, String notificationType, List<String> params) {
        this.notificationLanguage = notificationLanguage;
        this.notificationText = notificationText;
        this.notificationType = notificationType;
        this.params = params;
        this.dateTime = LocalDateTime.now();
    }

    public String getNotificationLanguage() {
        return notificationLanguage;
    }

    public void setNotificationLanguage(String notificationLanguage) {
        this.notificationLanguage = notificationLanguage;
    }

    public String getNotificationText() {
        return notificationText;
    }

    public void setNotificationText(String notificationText) {
        this.notificationText = notificationText;
    }

    public String getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(String notificationType) {
        this.notificationType = notificationType;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public List<String> getParams() {
        return params;
    }

    public void setParams(List<String> params) {
        this.params = params;
    }
}
