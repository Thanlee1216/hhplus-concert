package com.hhplus.concert.infrastructure.persistence.mapper;

import com.hhplus.concert.business.domain.ReservationDomain;
import com.hhplus.concert.infrastructure.entity.ReservationEntity;

import java.util.Optional;

public class ReservationEntityMapper {

    public static ReservationEntity toReservationEntity(ReservationDomain reservationDomain) {
        return new ReservationEntity(reservationDomain.reservationId(), reservationDomain.userId(), reservationDomain.concertId(), reservationDomain.concertOptionId(), reservationDomain.seatId(), reservationDomain.reservationTime(), reservationDomain.status());
    }

    public static ReservationDomain toReservationDomain(ReservationEntity reservationEntity) {
        return new ReservationDomain(reservationEntity.getReservationId(), reservationEntity.getUserId(), reservationEntity.getConcertId(), reservationEntity.getConcertOptionId(), reservationEntity.getSeatId(), reservationEntity.getReservationTime(), reservationEntity.getStatus());
    }

    public static ReservationDomain toReservationDomain(Optional<ReservationEntity> reservationEntity) {
        return new ReservationDomain(reservationEntity.get().getReservationId(), reservationEntity.get().getUserId(), reservationEntity.get().getConcertId(), reservationEntity.get().getConcertOptionId(), reservationEntity.get().getSeatId(), reservationEntity.get().getReservationTime(), reservationEntity.get().getStatus());
    }
}
