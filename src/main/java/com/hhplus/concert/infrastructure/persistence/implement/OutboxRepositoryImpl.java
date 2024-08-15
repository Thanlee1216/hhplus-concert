package com.hhplus.concert.infrastructure.persistence.implement;

import com.hhplus.concert.business.constant.OutboxStatusType;
import com.hhplus.concert.business.domain.OutboxDomain;
import com.hhplus.concert.business.repository.OutboxRepository;
import com.hhplus.concert.infrastructure.persistence.dataaccess.jpa.OutboxJpaRepository;
import com.hhplus.concert.infrastructure.persistence.mapper.OutboxEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class OutboxRepositoryImpl implements OutboxRepository {

    private final OutboxJpaRepository repository;

    @Override
    public void insertMessage(OutboxDomain domain) {
        repository.save(OutboxEntityMapper.domainToEntity(domain));
    }

    @Override
    public List<OutboxDomain> findByOutboxStatus(OutboxStatusType outboxStatusType) {
        return OutboxEntityMapper.entityToDomainList(repository.findByOutboxStatus(outboxStatusType));
    }

    @Override
    public void updateMessage(OutboxDomain outboxDomain) {
        repository.save(OutboxEntityMapper.domainToEntity(outboxDomain));
    }
}
