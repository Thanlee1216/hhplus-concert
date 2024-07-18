package com.hhplus.concert.infrastructure.persistence.mapper;

import com.hhplus.concert.business.domain.TicketDomain;
import com.hhplus.concert.infrastructure.entity.TicketEntity;

public class TicketEntityMapper {

    public static TicketEntity toTicketEntity(TicketDomain ticketDomain) {
        return new TicketEntity(ticketDomain.ticketId()
                , ticketDomain.userId()
                , ticketDomain.reservationId()
                , ticketDomain.concertId()
                , ticketDomain.concertName()
                , ticketDomain.concertOptionId()
                , ticketDomain.concertDate()
                , ticketDomain.seatId()
                , ticketDomain.seatNum()
                , ticketDomain.ticketStatus()
        );
    }

    public static TicketDomain toTicketDomain(TicketEntity entity) {
        return new TicketDomain(entity.getTicket_Id()
                , entity.getUserId()
                , entity.getReservationId()
                , entity.getConcertId()
                , entity.getConcertName()
                , entity.getConcertOptionId()
                , entity.getConcertOptionName()
                , entity.getConcertDate()
                , entity.getSeatId()
                , entity.getSeatNum()
                , null
                , entity.getStatus()
        );
    }
}
