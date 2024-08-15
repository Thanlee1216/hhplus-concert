package com.hhplus.concert.infrastructure.persistence.dataaccess.jpa;

import com.hhplus.concert.business.constant.OutboxStatusType;
import com.hhplus.concert.infrastructure.entity.OutboxEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OutboxJpaRepository extends JpaRepository<OutboxEntity, Long> {

    List<OutboxEntity> findByOutboxStatus(OutboxStatusType outboxStatusType);
}
