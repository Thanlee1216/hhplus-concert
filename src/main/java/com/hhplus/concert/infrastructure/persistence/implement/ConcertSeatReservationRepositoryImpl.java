package com.hhplus.concert.infrastructure.persistence.implement;

import com.hhplus.concert.business.repository.ConcertSeatReservationRepository;
import com.hhplus.concert.infrastructure.persistence.dataaccess.ConcertSeatReservationJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class ConcertSeatReservationRepositoryImpl implements ConcertSeatReservationRepository {

    private final ConcertSeatReservationJpaRepository jpaRepository;

}
