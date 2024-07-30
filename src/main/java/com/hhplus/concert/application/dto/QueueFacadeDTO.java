package com.hhplus.concert.application.dto;

public record QueueFacadeDTO(
        Long userId,
        Long userQueueCount,
        String token
) {

    public QueueFacadeDTO withToken(String token) {
        return new QueueFacadeDTO(userId, userQueueCount, token);
    }
}
