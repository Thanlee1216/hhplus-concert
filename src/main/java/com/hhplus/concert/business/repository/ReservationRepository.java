package com.hhplus.concert.business.repository;

import com.hhplus.concert.business.constant.ReservationStatusType;
import com.hhplus.concert.business.domain.ReservationDomain;

public interface ReservationRepository {
    ReservationDomain save(ReservationDomain reservationDomain);

    ReservationDomain findByUserIdAndSeatIdAndStatus(Long userId, Long seatId, ReservationStatusType status);

    ReservationDomain findById(Long reservationId);
}
