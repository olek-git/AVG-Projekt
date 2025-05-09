package com.example.ecommercesystem.dto;


public class StatusUpdateMessage {
    private String orderID;
    private String deliveryStatus;

    public StatusUpdateMessage(String orderID, String deliveryStatus) {
        this.orderID = orderID;
        this.deliveryStatus = deliveryStatus;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }
}