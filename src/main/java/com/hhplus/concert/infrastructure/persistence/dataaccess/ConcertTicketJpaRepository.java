package com.hhplus.concert.infrastructure.persistence.dataaccess;

import com.hhplus.concert.infrastructure.entity.ConcertTicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConcertTicketJpaRepository extends JpaRepository<ConcertTicketEntity, Long> {
}
