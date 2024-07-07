package com.hhplus.concert.infrastructure.persistence.implement;

import com.hhplus.concert.business.repository.ConcertHallSeatRepository;
import com.hhplus.concert.infrastructure.persistence.dataaccess.ConcertHallSeatJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class ConcertHallSeatRepositoryImpl implements ConcertHallSeatRepository {

    private final ConcertHallSeatJpaRepository jpaRepository;

}
