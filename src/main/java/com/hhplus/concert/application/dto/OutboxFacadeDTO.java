package com.hhplus.concert.application.dto;

import com.hhplus.concert.business.constant.OutboxStatusType;

public record OutboxFacadeDTO(
        Long outboxSeq
        , String topic
        , String topicKay
        , String payload
        , OutboxStatusType outboxStatus
) {

    public OutboxFacadeDTO withOutboxStatus(OutboxStatusType outboxStatus) {
        return new OutboxFacadeDTO(outboxSeq, topic, topicKay, payload, outboxStatus);
    }
}
