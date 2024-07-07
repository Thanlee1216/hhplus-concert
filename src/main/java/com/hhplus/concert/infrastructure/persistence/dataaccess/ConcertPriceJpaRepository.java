package com.hhplus.concert.infrastructure.persistence.dataaccess;

import com.hhplus.concert.infrastructure.entity.ConcertPriceEntity;
import com.hhplus.concert.infrastructure.entity.compositekey.ConcertPriceKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConcertPriceJpaRepository extends JpaRepository<ConcertPriceEntity, ConcertPriceKey> {
}
