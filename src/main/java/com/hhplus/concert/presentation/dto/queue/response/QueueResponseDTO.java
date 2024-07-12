package com.hhplus.concert.presentation.dto.queue.response;

import com.hhplus.concert.business.constant.QueueStatusType;

public record QueueResponseDTO(
        Long userId,
        Long queueNumber,
        QueueStatusType status,
        Long userQueueCount
) {
}
