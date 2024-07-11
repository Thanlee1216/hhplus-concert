package com.hhplus.concert.infrastructure.persistence.implement;

import com.hhplus.concert.business.repository.ConcertRepository;
import com.hhplus.concert.infrastructure.persistence.dataaccess.ConcertJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class ConcertRepositoryImpl implements ConcertRepository {

    private final ConcertJpaRepository jpaRepository;

}
