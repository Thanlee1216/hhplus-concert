package com.hhplus.concert.interfaces.presentation.dto.concert.response;

import java.sql.Timestamp;

public record ConcertResponseDTO(
        Long concert_id,
        String concert_name,
        Long concert_option_id,
        Timestamp concert_date,
        Timestamp concert_reservation_date
) {
}
