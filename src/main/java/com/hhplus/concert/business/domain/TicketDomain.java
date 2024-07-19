package com.hhplus.concert.business.domain;

import com.hhplus.concert.business.constant.TicketStatusType;

import java.sql.Timestamp;

public record TicketDomain(
        Long ticketId,
        Long userId,
        Long reservationId,
        Long concertId,
        String concertName,
        Long concertOptionId,
        String concertOptionName,
        Timestamp concertDate,
        Long seatId,
        String seatNum,
        Long seatPrice,
        TicketStatusType ticketStatus
) {
    public static TicketDomain of(ConcertDomain concertDomain, ConcertOptionDomain concertOptionDomain, ConcertSeatDomain concertSeatDomain) {
        return new TicketDomain(null,
                null,
                null,
                concertDomain.concertId(),
                concertDomain.concertName(),
                concertOptionDomain.concertOptionId(),
                concertOptionDomain.concertOptionName(),
                concertOptionDomain.concertDate(),
                concertSeatDomain.seatId(),
                concertSeatDomain.seatNum(),
                concertSeatDomain.seatPrice(),
                null
                );
    }

    public TicketDomain withIdsAndStatus(Long userId, Long reservationId, TicketStatusType ticketStatus) {
        return new TicketDomain(ticketId, userId, reservationId, concertId, concertName, concertOptionId, concertOptionName, concertDate, seatId, seatNum, seatPrice, ticketStatus);
    }

    public ConcertSeatDomain concertSeatDomain() {
        return new ConcertSeatDomain(seatId, concertId, concertOptionId, seatNum, seatPrice, null);
    }
}
