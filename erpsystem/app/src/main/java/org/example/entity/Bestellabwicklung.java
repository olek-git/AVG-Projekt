package org.example.entity;

import java.time.LocalDate;


public class Bestellabwicklung{
    private String orderId;
    private String customerId;
    private String productId;
    private OrderStatus orderStatus;
    private LocalDate shippingDate;

    public Bestellabwicklung(String orderId, String customerId, String productId, OrderStatus orderStatus, LocalDate shippingDate) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.productId = productId;
        this.orderStatus = orderStatus;
        this.shippingDate = shippingDate;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public LocalDate getShippingDate() {
        return shippingDate;
    }

    public void setShippingDate(LocalDate shippingDate) {
        this.shippingDate = shippingDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bestellabwicklung that = (Bestellabwicklung) o;

        if (!orderId.equals(that.orderId)) return false;
        if (!customerId.equals(that.customerId)) return false;
        if (!productId.equals(that.productId)) return false;
        if (orderStatus != that.orderStatus) return false;
        return shippingDate.equals(that.shippingDate);
    }

    @Override
    public int hashCode() {
        int result = orderId.hashCode();
        result = 31 * result + customerId.hashCode();
        result = 31 * result + productId.hashCode();
        result = 31 * result + orderStatus.hashCode();
        result = 31 * result + shippingDate.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Bestellabwicklung{" +
                "orderId='" + orderId + '\'' +
                ", customerId='" + customerId + '\'' +
                ", productId='" + productId + '\'' +
                ", orderStatus=" + orderStatus +
                ", shippingDate=" + shippingDate +
                '}';
    }
}