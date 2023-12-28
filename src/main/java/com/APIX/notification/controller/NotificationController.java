package com.APIX.notification.controller;


import com.APIX.notification.model.Notification;
import com.APIX.notification.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/notifications")
@RestController
public class NotificationController {
    @Autowired
    private NotificationService notificationService;

    @PostMapping
    public ResponseEntity<Void> addNotification(@RequestBody Notification notification) {
        boolean added = notificationService.addNotification(notification);
        if(added){
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }


    @GetMapping
    public ResponseEntity<List<Notification>> getNotifications() {
        List<Notification> notifications = notificationService.getNotifications();
        return ResponseEntity.ok(notifications);
    }


    @GetMapping(path = "{id}")
    public ResponseEntity<Notification> getNotification(@PathVariable("id") Long id) {
        Notification product = notificationService.getNotification(id);
        if (product != null) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping(path = "{id}")
    public ResponseEntity<Void> deleteNotification(@PathVariable("id") Long id) {
        boolean deleted = notificationService.deleteNotification(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<Void> updateNotification(@RequestBody Notification notification) {
        boolean updated = notificationService.updateNotification(notification);
        if (updated) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
