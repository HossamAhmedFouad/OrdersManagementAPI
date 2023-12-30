package com.APIX.notification.Factory;

import com.APIX.ObserverPattern.Observer;
import com.APIX.notification.model.Notification;
import com.APIX.notification.service.NotificationService;
import com.APIX.notification.service.NotificationStat;
import com.APIX.order.model.Order;
import com.APIX.order.model.OrderState;
import com.APIX.user.model.Language;
import com.APIX.user.model.User;
import com.APIX.user.service.UserService;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public abstract class NotificationFactory implements Observer {
    Map<OrderState, Map<Language, String>> stateTemplate = new HashMap<>();
    Map<Language, String> placementLang = new HashMap<>();
    Map<Language, String> readyLang = new HashMap<>();
    Map<Language, String> shippingLang = new HashMap<>();
    Map<Language, String> cancellationLang = new HashMap<>();

    String notificationText;

    public static String fillPlaceholders(String template, Object... args) {
        return String.format(template, args);
    }

    @Override
    public void update(Language language, Order order){
        Notification notification = createTemplate(language,order);
        NotificationService.addNotificationToQueue(notification);
    }

    Notification createTemplate(Language lang, Order order) {
        User user= UserService.getUserById(order.getUserID());
        notificationText = getStateTemplate(lang,order.getStatus());
        notificationText = fillPlaceholders(notificationText, user.getUsername(), order.getId(), LocalDateTime.now());
        addToStat(user);
        NotificationStat.addTemp(order.getStatus());
        return createNotification(lang,notificationText, order.getStatus(),user);
    }
    abstract Notification createNotification(Language lang,String notificationText,OrderState orderState,User user );
    abstract void addToStat(User user);
    abstract String getStateTemplate(Language lang,OrderState orderState);

}
