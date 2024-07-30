package com.hhplus.concert.infrastructure.persistence.dataaccess.jpa;

import com.hhplus.concert.infrastructure.entity.PaymentHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentHistoryJpaRepository extends JpaRepository<PaymentHistoryEntity, Long> {
}
