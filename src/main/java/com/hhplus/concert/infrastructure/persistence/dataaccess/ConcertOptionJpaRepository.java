package com.hhplus.concert.infrastructure.persistence.dataaccess;

import com.hhplus.concert.infrastructure.entity.ConcertOptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.util.List;

public interface ConcertOptionJpaRepository extends JpaRepository<ConcertOptionEntity, Long> {
    List<ConcertOptionEntity> findByConcertIdAndConcertReservationDateBefore(Long concertId, Timestamp timestamp);

    ConcertOptionEntity findByConcertIdAndConcertOptionId(Long concertId, Long concertOptionId);
}
