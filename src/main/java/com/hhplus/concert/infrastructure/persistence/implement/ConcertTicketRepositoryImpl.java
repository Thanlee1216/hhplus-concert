package com.hhplus.concert.infrastructure.persistence.implement;

import com.hhplus.concert.business.repository.ConcertTicketRepository;
import com.hhplus.concert.infrastructure.persistence.dataaccess.ConcertTicketJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class ConcertTicketRepositoryImpl implements ConcertTicketRepository {

    private final ConcertTicketJpaRepository jpaRepository;

}
