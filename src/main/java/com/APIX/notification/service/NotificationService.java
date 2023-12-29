package com.APIX.notification.service;


import com.APIX.CustomRepository;
import com.APIX.notification.dao.NotificationsDAS;
import com.APIX.notification.model.Notification;
import com.APIX.order.model.Order;
import com.APIX.order.service.OrderService;
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
        NotificationsDAS notificationsDAS = (NotificationsDAS) notificationDas;
        Notification notification = notificationsDAS.popNotification();
        if (notification != null) {
            notificationsDAS.save(notification);
            System.out.println("Popped notification: " + notification.getId());
        }
    }
    public static boolean addNotification(Notification notification){
        return notificationDas.save(notification);
    }

    public static boolean addNotificationToQueue(Notification notification){
        NotificationsDAS notificationsDAS = (NotificationsDAS) notificationDas;
        notificationsDAS.addNotificationToQueue(notification);
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
