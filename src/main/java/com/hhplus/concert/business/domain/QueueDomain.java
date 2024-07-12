package com.hhplus.concert.business.domain;

import com.hhplus.concert.business.constant.QueueStatusType;

public record QueueDomain(
        Long queueNumber,
        Long userId,
        QueueStatusType status,
        Long userQueueCount
) {
    public QueueDomain withStatus(QueueStatusType status) {
        return new QueueDomain(queueNumber, userId, status, userQueueCount);
    }

    public QueueDomain withUserQueueCount(Long userQueueCount) {
        return new QueueDomain(queueNumber, userId, status, userQueueCount);
    }
}
