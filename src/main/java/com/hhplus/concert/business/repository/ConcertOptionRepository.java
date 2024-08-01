package com.hhplus.concert.business.repository;

import com.hhplus.concert.business.domain.ConcertOptionDomain;

import java.sql.Timestamp;
import java.util.List;

public interface ConcertOptionRepository {
    List<ConcertOptionDomain> findByConcertIdAndConcertReservationDateBefore(Long concertId, Long timestamp);

    ConcertOptionDomain findByConcertIdAndConcertOptionId(Long concertId, Long concertOptionId);
}
