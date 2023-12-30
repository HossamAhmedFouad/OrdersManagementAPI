package com.APIX.notification.dao;

import com.APIX.CustomRepository;
import com.APIX.notification.model.Notification;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@Repository("notificationDAO")
public class NotificationsDAO implements CustomRepository<Notification, Long> {
    List<Notification> notificationDB = new ArrayList<>();

    Queue<Notification> notificationsQueue = new LinkedList<>();

    @Override
    public  Notification getById(Long id) {
        for(Notification notification : notificationDB){
            if(notification.getId().equals(id)){
                return notification;
            }
        }
        return null;
    }

    public Notification popNotification(){
        return notificationsQueue.poll();
    }

    public void addNotificationToQueue(Notification notification){
        notificationsQueue.add(notification);
    }

    @Override
    public boolean save(Notification entity) {
        if(entity == null) return false;
        notificationDB.add(entity);
        return true;
    }

    @Override
    public List<Notification> getAll() {
        return notificationDB;
    }

    @Override
    public boolean update(Notification updatedEntity) {
        for(int i = 0; i < notificationDB.size(); i++){
            if(notificationDB.get(i).getId().equals(updatedEntity.getId())){
                notificationDB.set(i, updatedEntity);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean delete(Long id) {
        for(int i = 0; i < notificationDB.size(); i++){
            if(notificationDB.get(i).getId().equals(id)){
                notificationDB.remove(i);
                return true;
            }
        }
        return false;
    }
}
