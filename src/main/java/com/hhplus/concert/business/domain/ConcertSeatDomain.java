package com.hhplus.concert.business.domain;

import com.hhplus.concert.business.constant.ReservationStatusType;

import java.sql.Timestamp;

public record ConcertSeatDomain(
        Long seatId,
        Long concertId,
        Long concertOptionId,
        String seatNum,
        Long seatPrice,
        ReservationStatusType seatStatus
) {
    public ConcertSeatDomain withSeatStatus(ReservationStatusType seatStatus) {
        return new ConcertSeatDomain(seatId, concertId, concertOptionId, seatNum, seatPrice, seatStatus);
    }

    public ReservationDomain convertToReservationDomain() {
        return new ReservationDomain(null, null, concertId, concertOptionId, seatId, new Timestamp(System.currentTimeMillis()), seatStatus);
    }
}
