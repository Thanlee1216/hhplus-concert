package com.hhplus.concert.interfaces.presentation.dto.concert.response;

import com.hhplus.concert.business.constant.ReservationStatusType;

public record ConcertSeatResponseDTO(
        Long seat_id,
        String seat_num,
        Long seat_price,
        ReservationStatusType seat_status
) {
}
