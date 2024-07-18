package com.hhplus.concert.interfaces.presentation.dto.payment.response;

public record PaymentResponseDTO(
        Long user_id,
        Long concert_seat_id,
        String seat_status
) {
}
