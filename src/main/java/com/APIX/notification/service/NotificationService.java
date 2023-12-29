package com.APIX.notification.service;


import com.APIX.CustomRepository;
import com.APIX.notification.dao.NotificationsDAO;
import com.APIX.notification.model.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {
    @Autowired
    private static CustomRepository<Notification, Long> notificationDas;
    @Autowired
    public NotificationService(CustomRepository<Notification, Long> notificationDas){
        NotificationService.notificationDas = notificationDas;
    }

    @Scheduled(fixedDelay = 5000)
    public void processNotifications() {
        NotificationsDAO notificationsDAO = (NotificationsDAO) notificationDas;
        Notification notification = notificationsDAO.popNotification();
        if (notification != null) {
            notificationsDAO.save(notification);
            System.out.println("Popped notification: " + notification.getId());
        }
    }

    public static boolean addNotification(Notification notification){
        return notificationDas.save(notification);
    }

    public static boolean addNotificationToQueue(Notification notification){
        NotificationsDAO notificationsDAO = (NotificationsDAO) notificationDas;
        notificationsDAO.addNotificationToQueue(notification);
        return true;
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
