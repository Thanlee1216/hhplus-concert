package com.hhplus.concert.application.dto;

import com.hhplus.concert.business.constant.ReservationStatusType;

import java.sql.Timestamp;

public record ConcertFacadeDTO(
        Long userId,
        Long concertId,
        String concertName,
        Long concertOptionId,
        String concertOptionName,
        Timestamp concertDate,
        Timestamp concertReservationDate,
        Long seatId,
        String seatNum,
        Long seatPrice,
        ReservationStatusType seatStatus
) {
}
