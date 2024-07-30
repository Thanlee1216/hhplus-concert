package com.hhplus.concert.infrastructure.persistence.implement;

import com.hhplus.concert.business.domain.ConcertOptionDomain;
import com.hhplus.concert.business.repository.ConcertOptionRepository;
import com.hhplus.concert.infrastructure.persistence.dataaccess.jpa.ConcertOptionJpaRepository;
import com.hhplus.concert.infrastructure.persistence.mapper.ConcertOptionEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class ConcertOptionRepositoryImpl implements ConcertOptionRepository {

    private final ConcertOptionJpaRepository jpaRepository;

    @Override
    public List<ConcertOptionDomain> findByConcertIdAndConcertReservationDateBefore(Long concertId, Timestamp timestamp) {
        return ConcertOptionEntityMapper.toConcertOptionDomainList(jpaRepository.findByConcertIdAndConcertReservationDateBefore(concertId, timestamp));
    }

    @Override
    public ConcertOptionDomain findByConcertIdAndConcertOptionId(Long concertId, Long concertOptionId) {
        return ConcertOptionEntityMapper.toConcertOptionDomain(jpaRepository.findByConcertIdAndConcertOptionId(concertId, concertOptionId));
    }
}
