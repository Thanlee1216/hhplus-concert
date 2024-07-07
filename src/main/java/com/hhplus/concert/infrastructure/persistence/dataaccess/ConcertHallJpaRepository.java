package com.hhplus.concert.infrastructure.persistence.dataaccess;

import com.hhplus.concert.infrastructure.entity.ConcertHallEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConcertHallJpaRepository extends JpaRepository<ConcertHallEntity, Long> {
}
