package com.hhplus.concert.business.domain;

import com.hhplus.concert.business.constant.OutboxStatusType;

public record OutboxDomain(
        Long outboxSeq
        , String topic
        , String topicKey
        , String payload
        , OutboxStatusType outboxStatus
) {
}
