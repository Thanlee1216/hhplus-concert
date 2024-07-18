package com.hhplus.concert.infrastructure.persistence.dataaccess;

import com.hhplus.concert.business.constant.QueueStatusType;
import com.hhplus.concert.infrastructure.entity.UserQueueEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserQueueJpaRepository extends JpaRepository<UserQueueEntity, Long> {
    Long countByStatus(QueueStatusType status);

    Long countByQueueNumberLessThanEqualAndStatusEquals(Long queueNumber, QueueStatusType status);

    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE UserQueueEntity AS A SET A.status = :status WHERE TIMESTAMPDIFF(MINUTE, A.activeAt, CURRENT_TIMESTAMP()) > 0 AND A.status = 'RUN'")
    Integer expiredQueue(@Param("status") QueueStatusType status);
}
