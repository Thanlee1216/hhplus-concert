package com.hhplus.concert.infrastructure.persistence.mapper;

import com.hhplus.concert.business.domain.QueueDomain;
import com.hhplus.concert.infrastructure.entity.UserQueueEntity;

import java.sql.Timestamp;
import java.util.Optional;

public class QueueEntityMapper {

    public static QueueDomain toQueueDomain(Optional<UserQueueEntity> entity) {
        return new QueueDomain(entity.get().getQueueNumber(), entity.get().getUserId(), entity.get().getStatus(), null);
    }

    public static QueueDomain toQueueDomain(UserQueueEntity entity) {
        return new QueueDomain(entity.getQueueNumber(), entity.getUserId(), entity.getStatus(), null);
    }

    public static UserQueueEntity toQueueEntity(QueueDomain queueDomain) {
        return new UserQueueEntity(queueDomain.queueNumber(), queueDomain.userId(), new Timestamp(System.currentTimeMillis()), null, queueDomain.status());
    }
}
