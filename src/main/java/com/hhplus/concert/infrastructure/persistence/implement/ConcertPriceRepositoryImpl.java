package com.hhplus.concert.infrastructure.persistence.implement;

import com.hhplus.concert.business.repository.ConcertPriceRepository;
import com.hhplus.concert.infrastructure.persistence.dataaccess.ConcertPriceJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class ConcertPriceRepositoryImpl implements ConcertPriceRepository {

    private final ConcertPriceJpaRepository jpaRepository;

}
