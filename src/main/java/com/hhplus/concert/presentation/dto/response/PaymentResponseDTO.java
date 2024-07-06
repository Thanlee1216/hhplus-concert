package com.hhplus.concert.presentation.dto.response;

public record PaymentResponseDTO(
        long user_id,
        long concert_seat_id,
        String seat_status
) {
}
