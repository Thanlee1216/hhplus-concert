package com.hhplus.concert.infrastructure.persistence.dataaccess;

import com.hhplus.concert.business.constant.QueueStatusType;
import com.hhplus.concert.infrastructure.entity.UserQueueEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserQueueJpaRepository extends JpaRepository<UserQueueEntity, Long> {
    Long countByStatus(QueueStatusType status);

    Long countByQueueNumberLessThanEqualAndStatusEquals(Long queueNumber, QueueStatusType status);
}
