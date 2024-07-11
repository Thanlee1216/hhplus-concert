package com.hhplus.concert.infrastructure.persistence.dataaccess;

import com.hhplus.concert.infrastructure.entity.ConcertOptionEntity;
import com.hhplus.concert.infrastructure.entity.compositekey.ConcertOptionKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConcertOptionJpaRepository extends JpaRepository<ConcertOptionEntity, ConcertOptionKey> {
}
