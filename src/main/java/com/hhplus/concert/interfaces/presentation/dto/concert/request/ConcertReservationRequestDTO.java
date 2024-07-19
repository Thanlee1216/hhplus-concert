package com.hhplus.concert.interfaces.presentation.dto.concert.request;

public record ConcertReservationRequestDTO(
        Long userId,
        Long concertId,
        Long concertOptionId,
        Long seatId
) {
}
