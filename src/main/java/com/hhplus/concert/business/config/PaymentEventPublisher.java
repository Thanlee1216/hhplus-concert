package com.hhplus.concert.business.config;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

// 이벤트 발행서비스
@Component
public class PaymentEventPublisher {
    private final ApplicationEventPublisher applicationEventPublisher;

    public PaymentEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void success(PaymentSuccessEvent event) {
        applicationEventPublisher.publishEvent(event);
    }
}
