package com.hhplus.concert.infrastructure.persistence.mapper;

import com.hhplus.concert.business.domain.ConcertSeatDomain;
import com.hhplus.concert.infrastructure.entity.ConcertSeatEntity;

import java.util.List;
import java.util.Optional;

public class ConcertSeatEntityMapper {

    public static List<ConcertSeatDomain> toConcertSeatDomainList(List<ConcertSeatEntity> entityList) {
        return entityList.stream()
                .map(entity -> new ConcertSeatDomain(
                        entity.getSeatId(),
                        entity.getConcertId(),
                        entity.getConcertOptionId(),
                        entity.getSeatNum(),
                        entity.getSeatPrice(),
                        entity.getSeatStatus()
                )).toList();
    }

    public static ConcertSeatEntity toConcertSeatEntity(ConcertSeatDomain domain) {
        return new ConcertSeatEntity(domain.seatId(), domain.concertId(), domain.concertOptionId(), domain.seatNum(), domain.seatPrice(), domain.seatStatus());
    }

    public static ConcertSeatDomain toConcertSeatDomain(Optional<ConcertSeatEntity> entity) {
        return new ConcertSeatDomain(entity.get().getSeatId(), entity.get().getConcertId(), entity.get().getConcertOptionId(), entity.get().getSeatNum(), entity.get().getSeatPrice(), entity.get().getSeatStatus());
    }

    public static ConcertSeatDomain toConcertSeatDomain(ConcertSeatEntity entity) {
        return new ConcertSeatDomain(entity.getSeatId(), entity.getConcertId(), entity.getConcertOptionId(), entity.getSeatNum(), entity.getSeatPrice(), entity.getSeatStatus());
    }
}
