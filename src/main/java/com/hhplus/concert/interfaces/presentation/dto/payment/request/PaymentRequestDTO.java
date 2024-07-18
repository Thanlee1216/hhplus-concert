package com.hhplus.concert.interfaces.presentation.dto.payment.request;

public record PaymentRequestDTO(
        Long userId,
        Long seatId
) {
}
