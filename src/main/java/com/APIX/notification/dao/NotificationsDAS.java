package com.APIX.notification.dao;

import com.APIX.notification.model.Notification;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class NotificationsDAS {
    List<Notification> notificationList = new ArrayList<>();
    Queue<Notification> q = new LinkedList<>();
}
