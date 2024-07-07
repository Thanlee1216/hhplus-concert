package com.hhplus.concert.infrastructure.persistence.dataaccess;

import com.hhplus.concert.infrastructure.entity.ConcertHallSeatEntity;
import com.hhplus.concert.infrastructure.entity.compositekey.ConcertHallSeatKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConcertHallSeatJpaRepository extends JpaRepository<ConcertHallSeatEntity, ConcertHallSeatKey> {
}
