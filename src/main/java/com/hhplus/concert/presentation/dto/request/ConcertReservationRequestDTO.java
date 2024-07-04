package com.hhplus.concert.presentation.dto.request;

public record ConcertReservationRequestDTO(
        long concert_seat_id,
        String token
) {
}
