package com.hhplus.concert.business.repository;

import com.hhplus.concert.business.domain.ConcertDomain;

public interface ConcertRepository {
    ConcertDomain findById(Long concertId);
}
