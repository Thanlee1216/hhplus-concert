package com.hhplus.concert.presentation.dto.request;

public record PaymentRequestDTO(
        long user_id,
        long concert_seat_id
) {
}
