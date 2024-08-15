package com.hhplus.concert.application.event;

import com.hhplus.concert.business.constant.OutboxStatusType;
import com.hhplus.concert.business.domain.OutboxDomain;
import com.hhplus.concert.business.service.OutboxService;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

// 이벤트 구독서비스
@Component
@RequiredArgsConstructor
public class BalanceEventListener {

    private final DataPlatformSendService sendService;

    private final OutboxService outboxService;

    @KafkaListener(topics = "balance-success", groupId = "balance-group")
    public void insertBalanceHistory(BalanceSuccessEvent event) {
        BalanceSuccessPayload payload = new BalanceSuccessPayload(event.getUserId(), event.getAmount(), event.getTransactionType(), event.getTransactionDate());
        sendService.insertBalanceHistory(payload);
    }

    //kafka message의 key를 가져오는 Listener
    @KafkaListener(topics = "balance-success", groupId = "outbox-group")
    public void updateOutboxStatus(ConsumerRecord<String, BalanceSuccessEvent> record) {
        String key = record.key();
        BalanceSuccessEvent event = record.value();
        outboxService.updateMessage(new OutboxDomain(event.getOutboxSeq(), "balance-success", key, null, OutboxStatusType.PUBLISHED));
    }
}
