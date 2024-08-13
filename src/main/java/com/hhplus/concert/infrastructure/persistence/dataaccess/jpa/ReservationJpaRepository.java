package com.hhplus.concert.infrastructure.persistence.dataaccess.jpa;

import com.hhplus.concert.business.constant.ReservationStatusType;
import com.hhplus.concert.infrastructure.entity.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationJpaRepository extends JpaRepository<ReservationEntity, Long> {
    ReservationEntity findByUserIdAndSeatIdAndStatus(Long userId, Long seatId, ReservationStatusType status);
}
