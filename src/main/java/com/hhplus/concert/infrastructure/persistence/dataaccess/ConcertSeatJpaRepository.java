package com.hhplus.concert.infrastructure.persistence.dataaccess;

import com.hhplus.concert.infrastructure.entity.ConcertSeatEntity;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import java.util.List;
import java.util.Optional;

public interface ConcertSeatJpaRepository extends JpaRepository<ConcertSeatEntity, Long> {
    List<ConcertSeatEntity> findByConcertIdAndConcertOptionId(Long concertId, Long concertOptionId);

    @Override
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<ConcertSeatEntity> findById(Long aLong);
}
