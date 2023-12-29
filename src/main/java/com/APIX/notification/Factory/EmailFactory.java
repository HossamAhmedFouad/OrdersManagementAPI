package com.APIX.notification.Factory;

import com.APIX.notification.model.EmailNotification;
import com.APIX.notification.model.Notification;
import com.APIX.notification.service.NotificationStat;
import com.APIX.order.model.Order;
import com.APIX.order.model.OrderState;
import com.APIX.user.model.Language;
import com.APIX.user.model.User;
import com.APIX.user.service.UserService;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class EmailFactory extends NotificationFactory {

    Map<OrderState, Map<Language, String>> stateTemplate = new HashMap<>();
    public EmailFactory(){
        // method to be clean more
        placementLang.put(Language.ENG, "Dear %s, your order #%d has been processed on %s");
        placementLang.put(Language.AR, "عزيزي %s، تم معالجة طلبك رقم #%d في تاريخ %s.");

        shipmentLang.put(Language.ENG, "Dear %s, your order #%d has been shipped on %s.");
        shipmentLang.put(Language.AR, "عزيزي %s، تم شحن طلبك رقم #%d في تاريخ %s.");

        cancellationLang.put(Language.ENG, "Dear %s, your order #%d has been canceled as of %s.");
        cancellationLang.put(Language.AR, "عزيزي %s، تم إلغاء طلبك رقم #%d اعتبارًا من %s.");
        stateTemplate.put(OrderState.PLACED,placementLang);
        stateTemplate.put(OrderState.SHIPPED,shipmentLang);
        stateTemplate.put(OrderState.CANCELED,cancellationLang);

    }

    void addToStat(User user) {
        NotificationStat.addEmail(user.getEmail());
    }

    String getStateTemplate(Language lang,OrderState orderState) {
        return stateTemplate.get(orderState).get(lang);
    }

    Notification createNotification(Language lang,String notificationText,OrderState orderState,User user ){
        return new EmailNotification(lang,notificationText, orderState,user.getEmail());
    };


}
