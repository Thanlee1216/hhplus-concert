package com.hhplus.concert.infrastructure.persistence.dataaccess;

import com.hhplus.concert.infrastructure.entity.ReservationEntity;
import com.hhplus.concert.infrastructure.entity.compositekey.ConcertSeatReservationKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConcertSeatReservationJpaRepository extends JpaRepository<ReservationEntity, ConcertSeatReservationKey> {
}
