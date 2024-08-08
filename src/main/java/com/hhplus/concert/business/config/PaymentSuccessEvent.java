package com.hhplus.concert.business.config;

public class PaymentSuccessEvent {
    private final String orderKey;
    private final String paymentKey;

    public PaymentSuccessEvent(String orderKey, String paymentKey) {
        this.orderKey = orderKey;
        this.paymentKey = paymentKey;
    }

    public String getOrderKey() {
        return orderKey;
    }

    public String getPaymentKey() {
        return paymentKey;
    }
}
