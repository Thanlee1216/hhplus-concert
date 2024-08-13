package com.hhplus.concert.infrastructure.persistence.dataaccess.jpa;

import com.hhplus.concert.infrastructure.entity.ConcertSeatEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConcertSeatJpaRepository extends JpaRepository<ConcertSeatEntity, Long> {
    List<ConcertSeatEntity> findByConcertIdAndConcertOptionId(Long concertId, Long concertOptionId);
}
