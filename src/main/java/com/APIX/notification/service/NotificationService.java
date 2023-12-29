package com.APIX.notification.service;


import com.APIX.notification.dao.NotificationsDAS;
import com.APIX.notification.model.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {
    @Autowired
    private static NotificationsDAS notificationDas;

    public static boolean addNotification(Notification notification){
        return notificationDas.save(notification);
    }

    public static List<Notification> getNotifications(){
        return notificationDas.getAll();
    }

    public static Notification getNotification(Long id){
        return notificationDas.getById(id);
    }

    public static boolean deleteNotification(Long id){
        return notificationDas.delete(id);
    }

    public static boolean updateNotification(Notification notification){
        return notificationDas.update(notification);
    }
}
