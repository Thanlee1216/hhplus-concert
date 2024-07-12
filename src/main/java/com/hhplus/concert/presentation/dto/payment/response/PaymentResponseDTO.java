package com.hhplus.concert.presentation.dto.payment.response;

public record PaymentResponseDTO(
        Long user_id,
        Long concert_seat_id,
        String seat_status
) {
}
