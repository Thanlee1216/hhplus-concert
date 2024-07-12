package com.hhplus.concert.business.repository;

import com.hhplus.concert.business.domain.TicketDomain;

public interface TicketRepository {
    TicketDomain insertTicket(TicketDomain ticketDomain);
}
