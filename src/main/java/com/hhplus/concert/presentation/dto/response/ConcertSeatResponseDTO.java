package com.hhplus.concert.presentation.dto.response;

public record ConcertSeatResponseDTO(
        long concert_seat_id,
        String concert_seat_num,
        long concert_seat_price,
        String seat_status
) {
}
