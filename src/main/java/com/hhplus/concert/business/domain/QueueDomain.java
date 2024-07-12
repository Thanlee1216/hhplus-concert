package com.hhplus.concert.business.domain;

import com.hhplus.concert.business.constant.QueueStatusType;

import java.sql.Timestamp;

public record QueueDomain(
        Long queueNumber,
        Long userId,
        Timestamp createdAt,
        Timestamp activeAt,
        QueueStatusType status,
        Long userQueueCount
) {
    public QueueDomain withStatus(QueueStatusType status) {
        return new QueueDomain(queueNumber, userId, createdAt, activeAt, status, userQueueCount);
    }

    public QueueDomain withCreatedAt(Timestamp createdAt) {
        return new QueueDomain(queueNumber, userId, createdAt, activeAt, status, userQueueCount);
    }

    public QueueDomain withActiveAt(Timestamp activeAt) {
        return new QueueDomain(queueNumber, userId, createdAt, activeAt, status, userQueueCount);
    }

    public QueueDomain withUserQueueCount(Long userQueueCount) {
        return new QueueDomain(queueNumber, userId, createdAt, activeAt,status, userQueueCount);
    }
}
