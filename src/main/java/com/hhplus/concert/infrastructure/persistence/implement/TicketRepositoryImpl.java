package com.hhplus.concert.infrastructure.persistence.implement;

import com.hhplus.concert.business.domain.TicketDomain;
import com.hhplus.concert.business.repository.TicketRepository;
import com.hhplus.concert.infrastructure.persistence.dataaccess.TicketJpaRepository;
import com.hhplus.concert.infrastructure.persistence.mapper.TicketEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class TicketRepositoryImpl implements TicketRepository {

    private final TicketJpaRepository jpaRepository;

    @Override
    public TicketDomain insertTicket(TicketDomain ticketDomain) {
        return TicketEntityMapper.toTicketDomain(jpaRepository.save(TicketEntityMapper.toTicketEntity(ticketDomain)));
    }
}
