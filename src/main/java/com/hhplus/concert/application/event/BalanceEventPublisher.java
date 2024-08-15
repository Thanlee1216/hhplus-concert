package com.hhplus.concert.application.event;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

// 이벤트 발행서비스
@Component
public class BalanceEventPublisher {

    private static final String TOPIC = "balance-success";

    private final KafkaTemplate<String, BalanceSuccessEvent> kafkaTemplate;

    public BalanceEventPublisher(KafkaTemplate<String, BalanceSuccessEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void success(BalanceSuccessEvent event) {
        kafkaTemplate.send(TOPIC, event.getUserId().toString(), event);
    }
}
