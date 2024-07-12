package com.hhplus.concert.business.repository;

import com.hhplus.concert.business.domain.ConcertSeatDomain;

import java.util.List;

public interface ConcertSeatRepository {
    List<ConcertSeatDomain> findByConcertIdAndConcertOptionId(Long concertId, Long concertOptionId);

    ConcertSeatDomain updateStatus(ConcertSeatDomain concertSeatDomain);

    ConcertSeatDomain findById(Long seatId);
}
