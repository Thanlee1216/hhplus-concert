package com.hhplus.concert.infrastructure.persistence.mapper;

import com.hhplus.concert.business.domain.ConcertOptionDomain;
import com.hhplus.concert.infrastructure.entity.ConcertOptionEntity;

import java.util.List;

public class ConcertOptionEntityMapper {

    public static List<ConcertOptionDomain> toConcertOptionDomainList(List<ConcertOptionEntity> entityList) {
        return entityList.stream()
                .map(entity -> new ConcertOptionDomain(
                        entity.getConcertId(),
                        entity.getConcertOptionId(),
                        entity.getConcertOptionName(),
                        entity.getConcertDate(),
                        entity.getConcertReservationDate()
                )).toList();
    }

    public static ConcertOptionDomain toConcertOptionDomain(ConcertOptionEntity concertOptionEntity) {
        return new ConcertOptionDomain(concertOptionEntity.getConcertId(), concertOptionEntity.getConcertOptionId(), concertOptionEntity.getConcertOptionName(), concertOptionEntity.getConcertDate(), concertOptionEntity.getConcertReservationDate());
    }
}
