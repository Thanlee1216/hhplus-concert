package com.hhplus.concert.application.dto;

import com.hhplus.concert.business.constant.QueueStatusType;

public record QueueFacadeDTO(
        Long queueNumber,
        Long userId,
        QueueStatusType status,
        Long userQueueCount
) {
}
