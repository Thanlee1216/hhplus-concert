package com.hhplus.concert.business.domain;

import com.hhplus.concert.business.constant.ReservationStatusType;

import java.sql.Timestamp;

public record ReservationDomain(
        Long reservationId,
        Long userId,
        Long concertId,
        Long concertOptionId,
        Long seatId,
        Timestamp reservationTime,
        ReservationStatusType status
) {
    public ReservationDomain withUserId(Long userId) {
        return new ReservationDomain(reservationId, userId, concertId, concertOptionId, seatId, reservationTime, status);
    }

    public ReservationDomain withReservationTime(Timestamp reservationTime) {
        return new ReservationDomain(reservationId, userId, concertId, concertOptionId, seatId, reservationTime, status);
    }

    public ReservationDomain withStatus(ReservationStatusType status) {
        return new ReservationDomain(reservationId, userId, concertId, concertOptionId, seatId, reservationTime, status);
    }
}
