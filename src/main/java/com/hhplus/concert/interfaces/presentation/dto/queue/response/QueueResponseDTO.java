package com.hhplus.concert.interfaces.presentation.dto.queue.response;

import com.hhplus.concert.business.constant.QueueStatusType;

public record QueueResponseDTO(
        Long userId,
        Long queueNumber,
        QueueStatusType status,
        Long userQueueCount
) {
}
