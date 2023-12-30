package com.APIX.notification.Factory;

import com.APIX.notification.model.Notification;
import com.APIX.notification.model.SMSNotification;
import com.APIX.notification.service.NotificationStat;
import com.APIX.order.model.OrderState;
import com.APIX.user.model.Language;
import com.APIX.user.model.User;

public class SMSFactory extends NotificationFactory {

    public SMSFactory(){
        placementLang.put(Language.ENG, "Dear %s, your order #%d has been placed on %s.");
        placementLang.put(Language.AR, "عزيزي %s، تم تقديم طلبك #%d في %s.");

        readyLang.put(Language.ENG, "Hello %s, your order #%d is now available for pickup since %s. It's no longer in transit.");
        readyLang.put(Language.AR, "مرحبًا %s، طلبك #%d متاح الآن للاستلام منذ %s. لم يعد في عملية الشحن.");

        shippingLang.put(Language.ENG, "Hello %s, your order #%d is being shipped on %s");
        shippingLang.put(Language.AR, "مرحبًا %s، يتم شحن طلبك #%d في %s.");

        cancellationLang.put(Language.ENG, "Hi %s, your order #%d has been canceled on %s.");
        cancellationLang.put(Language.AR, "مرحبًا %s، تم إلغاء طلبك #%d في %s.");

        stateTemplate.put(OrderState.PLACED,placementLang);
        stateTemplate.put(OrderState.READY, readyLang);
        stateTemplate.put(OrderState.SHIPPING, shippingLang);
        stateTemplate.put(OrderState.CANCELED,cancellationLang);
    }



    void addToStat(User user) {
        NotificationStat.addPhone(user.getPhoneNumber());
    }

    @Override
    String getStateTemplate(Language lang,OrderState orderState) {
        return stateTemplate.get(orderState).get(lang);
    }

    Notification createNotification(Language lang,String notificationText,OrderState orderState,User user ){
        return new SMSNotification(lang, notificationText, orderState,user.getPhoneNumber());
    };

}
