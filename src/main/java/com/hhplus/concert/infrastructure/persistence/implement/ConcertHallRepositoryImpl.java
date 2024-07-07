package com.hhplus.concert.infrastructure.persistence.implement;

import com.hhplus.concert.business.repository.ConcertHallRepository;
import com.hhplus.concert.infrastructure.persistence.dataaccess.ConcertHallJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class ConcertHallRepositoryImpl implements ConcertHallRepository {

    private final ConcertHallJpaRepository jpaRepository;

}
