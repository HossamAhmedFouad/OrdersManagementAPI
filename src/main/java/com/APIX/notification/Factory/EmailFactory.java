package com.APIX.notification.Factory;

import com.APIX.notification.model.EmailNotification;
import com.APIX.notification.model.Notification;
import com.APIX.notification.service.NotificationStat;
import com.APIX.order.model.OrderState;
import com.APIX.user.model.Language;
import com.APIX.user.model.User;

public class EmailFactory extends NotificationFactory {

    public EmailFactory(){
        placementLang.put(Language.ENG, "Dear %s, your order #%d has been processed on %s");
        placementLang.put(Language.AR, "عزيزي %s، تم معالجة طلبك رقم #%d في تاريخ %s.");

        readyLang.put(Language.ENG, "Hello %s, your order #%d is no longer being shipped and is ready for pickup since %s.");
        readyLang.put(Language.AR, "مرحبًا %s، طلبك #%d لم يعد يتم شحنه وهو جاهز الآن للاستلام منذ %s.");

        shippingLang.put(Language.ENG, "Dear %s, your order #%d is being shipped on %s.");
        shippingLang.put(Language.AR, "عزيزي %s، يتم شحن طلبك رقم #%d في تاريخ %s.");

        cancellationLang.put(Language.ENG, "Dear %s, your order #%d has been canceled as of %s.");
        cancellationLang.put(Language.AR, "عزيزي %s، تم إلغاء طلبك رقم #%d اعتبارًا من %s.");

        stateTemplate.put(OrderState.PLACED,placementLang);
        stateTemplate.put(OrderState.READY, readyLang);
        stateTemplate.put(OrderState.SHIPPING, shippingLang);
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
    }


}
