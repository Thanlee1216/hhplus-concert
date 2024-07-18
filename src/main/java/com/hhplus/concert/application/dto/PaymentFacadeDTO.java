package com.hhplus.concert.application.dto;

public record PaymentFacadeDTO(
        Long userId,
        Long seatId,
        Long reservationId
) {
}
