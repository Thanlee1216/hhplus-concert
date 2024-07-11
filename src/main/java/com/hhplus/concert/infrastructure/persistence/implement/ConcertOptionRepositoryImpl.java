package com.hhplus.concert.infrastructure.persistence.implement;

import com.hhplus.concert.business.repository.ConcertOptionRepository;
import com.hhplus.concert.infrastructure.persistence.dataaccess.ConcertOptionJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class ConcertOptionRepositoryImpl implements ConcertOptionRepository {

    private final ConcertOptionJpaRepository jpaRepository;

}
