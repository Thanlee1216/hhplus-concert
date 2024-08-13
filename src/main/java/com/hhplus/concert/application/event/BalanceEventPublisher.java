package com.hhplus.concert.application.event;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

// 이벤트 발행서비스
@Component
public class BalanceEventPublisher {
    private final ApplicationEventPublisher applicationEventPublisher;

    public BalanceEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void success(BalanceSuccessEvent event) {
        applicationEventPublisher.publishEvent(event);
    }
}
