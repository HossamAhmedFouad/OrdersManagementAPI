package com.APIX.notification.service;


import com.APIX.notification.dao.NotificationsDAS;
import com.APIX.notification.model.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {
    @Autowired
    private NotificationsDAS notificationDas;

    public boolean addNotification(Notification notification){
        return notificationDas.save(notification);
    }

    public List<Notification> getNotifications(){
        return notificationDas.getAll();
    }

    public Notification getNotification(Long id){
        return notificationDas.getById(id);
    }

    public boolean deleteNotification(Long id){
        return notificationDas.delete(id);
    }

    public boolean updateNotification(Notification notification){
        return notificationDas.update(notification);
    }
}
