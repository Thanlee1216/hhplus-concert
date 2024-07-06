package com.hhplus.concert.presentation.dto.request;

public record TokenRequestDTO(
        long user_id,
        long concert_id,
        long concert_option_id,
        long queue_number
) {
}
