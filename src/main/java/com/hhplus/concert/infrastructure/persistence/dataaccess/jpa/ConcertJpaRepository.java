package com.hhplus.concert.infrastructure.persistence.dataaccess.jpa;

import com.hhplus.concert.infrastructure.entity.ConcertEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConcertJpaRepository extends JpaRepository<ConcertEntity, Long> {
}
