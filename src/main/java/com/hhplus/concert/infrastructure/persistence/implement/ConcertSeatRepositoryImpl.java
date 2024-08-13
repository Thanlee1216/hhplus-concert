package com.hhplus.concert.infrastructure.persistence.implement;

import com.hhplus.concert.business.domain.ConcertSeatDomain;
import com.hhplus.concert.business.repository.ConcertSeatRepository;
import com.hhplus.concert.infrastructure.persistence.dataaccess.jpa.ConcertSeatJpaRepository;
import com.hhplus.concert.infrastructure.persistence.mapper.ConcertSeatEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class ConcertSeatRepositoryImpl implements ConcertSeatRepository {

    private final ConcertSeatJpaRepository jpaRepository;

    @Override
    public ConcertSeatDomain findById(Long seatId) {
        return ConcertSeatEntityMapper.toConcertSeatDomain(jpaRepository.findById(seatId));
    }

    @Override
    public List<ConcertSeatDomain> findByConcertIdAndConcertOptionId(Long concertId, Long concertOptionId) {
        return ConcertSeatEntityMapper.toConcertSeatDomainList(jpaRepository.findByConcertIdAndConcertOptionId(concertId, concertOptionId));
    }

    @Override
    public ConcertSeatDomain updateStatus(ConcertSeatDomain concertSeatDomain) {
        return ConcertSeatEntityMapper.toConcertSeatDomain(jpaRepository.save(ConcertSeatEntityMapper.toConcertSeatEntity(concertSeatDomain)));
    }

}
