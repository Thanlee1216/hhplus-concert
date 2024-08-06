package com.hhplus.concert.business.domain;

public record QueueDomain(
        Long userId,
        Long userQueueCount,
        String token
) {

    public QueueDomain withUserQueueCount(Long userQueueCount) {
        return new QueueDomain(userId, userQueueCount, token);
    }

    public QueueDomain withToken(String token) {
        return new QueueDomain(userId, userQueueCount, token);
    }
}
