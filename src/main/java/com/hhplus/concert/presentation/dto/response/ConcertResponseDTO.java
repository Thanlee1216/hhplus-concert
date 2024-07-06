package com.hhplus.concert.presentation.dto.response;

import java.sql.Timestamp;

public record ConcertResponseDTO(
        long concert_id,
        String concert_name,
        long concert_option_id,
        Timestamp concert_date,
        Timestamp concert_reservation_date,
        long limit_seat
) {
}
