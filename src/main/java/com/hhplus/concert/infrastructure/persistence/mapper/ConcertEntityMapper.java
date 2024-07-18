package com.hhplus.concert.infrastructure.persistence.mapper;

import com.hhplus.concert.business.domain.ConcertDomain;
import com.hhplus.concert.infrastructure.entity.ConcertEntity;

import java.util.Optional;

public class ConcertEntityMapper {

    public static ConcertDomain toConcertDomain(Optional<ConcertEntity> entity) {
        return new ConcertDomain(entity.get().getConcertId(), entity.get().getConcertName());
    }
}
