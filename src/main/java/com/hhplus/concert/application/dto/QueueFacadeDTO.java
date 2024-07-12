package com.hhplus.concert.application.dto;

import com.hhplus.concert.business.constant.QueueStatusType;

import java.sql.Timestamp;

public record QueueFacadeDTO(
        Long queueNumber,
        Long userId,
        Timestamp createdAt,
        Timestamp activeAt,
        QueueStatusType status,
        Long userQueueCount
) {
}
