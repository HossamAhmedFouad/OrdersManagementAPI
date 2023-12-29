package com.APIX.notification.service;

import com.APIX.order.model.OrderState;

public class StatDTO {
    public StatDTO(String mostNotified, OrderState mostUsedTemplate) {
        this.mostNotified = mostNotified;
        this.mostUsedTemplate = mostUsedTemplate;
    }

    public StatDTO() {

    }

    public String getMostNotified() {
        return mostNotified;
    }

    public void setMostNotified(String mostNotified) {
        this.mostNotified = mostNotified;
    }

    public OrderState getMostUsedTemplate() {
        return mostUsedTemplate;
    }

    public void setMostUsedTemplate(OrderState mostUsedTemplate) {
        this.mostUsedTemplate = mostUsedTemplate;
    }

    String mostNotified;
    OrderState mostUsedTemplate;

}
