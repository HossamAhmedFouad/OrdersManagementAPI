package com.APIX.notification.model;


import java.time.LocalDateTime;


public class Notification {

    static Long nextID = 1L;
    Long id;
    String notificationLanguage;
    String notificationText;
    String notificationType; //TODO: implement Enum Classes instead of strings for Type and language
    LocalDateTime dateTime;


    public Notification(String notificationLanguage, String notificationText, String notificationType) {
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

}
