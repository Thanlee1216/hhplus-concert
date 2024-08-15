package com.hhplus.concert.infrastructure.persistence.mapper;

import com.hhplus.concert.business.domain.OutboxDomain;
import com.hhplus.concert.infrastructure.entity.OutboxEntity;

import java.util.List;

public class OutboxEntityMapper {

    public static OutboxEntity domainToEntity(OutboxDomain domain) {
        return new OutboxEntity(domain.outboxSeq(), domain.topic(), domain.topicKey(), domain.payload(), domain.outboxStatus());
    }

    public static List<OutboxDomain> entityToDomainList(List<OutboxEntity> entities) {
        return entities.stream()
                .map(entity -> new OutboxDomain(
                        entity.getOutboxSeq()
                        , entity.getTopic()
                        , entity.getTopicKey()
                        , entity.getPayload()
                        , entity.getOutboxStatus()
                    )
                ).toList();
    }
}
