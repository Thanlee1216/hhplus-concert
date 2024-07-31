package com.hhplus.concert.business.service;

import com.hhplus.concert.business.domain.ConcertOptionDomain;
import com.hhplus.concert.business.domain.ConcertSeatDomain;
import com.hhplus.concert.business.repository.ConcertOptionRepository;
import com.hhplus.concert.business.repository.ConcertSeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class ConcertService {

    @Autowired
    private ConcertOptionRepository concertOptionRepository;

    @Autowired
    private ConcertSeatRepository concertSeatRepository;

    @Cacheable(value = "Concert", key = "#concertId", cacheManager = "cacheManager")
    public List<ConcertOptionDomain> getConcertOptionList(Long concertId) {
        return concertOptionRepository.findByConcertIdAndConcertReservationDateBefore(concertId, new Timestamp(System.currentTimeMillis()));
    }

    public List<ConcertSeatDomain> getSeatList(Long concertId, Long concertOptionId) {
        return concertSeatRepository.findByConcertIdAndConcertOptionId(concertId, concertOptionId);
    }

    public ConcertSeatDomain updateSeatStatus(ConcertSeatDomain concertSeatDomain) {
        return concertSeatRepository.updateStatus(concertSeatDomain);
    }

    public ConcertSeatDomain getSeatInfo(Long seatId) {
        return concertSeatRepository.findById(seatId);
    }
}
