package com.hhplus.concert.interfaces.presentation.dto.queue.response;

public record QueueResponseDTO(
        Long userId,
        Long userQueueCount,
        String token
) {
}
