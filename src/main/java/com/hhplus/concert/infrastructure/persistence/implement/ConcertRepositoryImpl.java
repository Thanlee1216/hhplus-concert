package com.hhplus.concert.infrastructure.persistence.implement;

import com.hhplus.concert.business.domain.ConcertDomain;
import com.hhplus.concert.business.repository.ConcertRepository;
import com.hhplus.concert.infrastructure.persistence.dataaccess.jpa.ConcertJpaRepository;
import com.hhplus.concert.infrastructure.persistence.mapper.ConcertEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class ConcertRepositoryImpl implements ConcertRepository {

    private final ConcertJpaRepository jpaRepository;

    @Override
    public ConcertDomain findById(Long concertId) {
        return ConcertEntityMapper.toConcertDomain(jpaRepository.findById(concertId));
    }
}
