package com.hhplus.concert.infrastructure.persistence.implement;

import com.hhplus.concert.business.domain.BalanceHistoryDomain;
import com.hhplus.concert.business.repository.BalanceHistoryRepository;
import com.hhplus.concert.infrastructure.persistence.dataaccess.jpa.BalanceHistoryJpaRepository;
import com.hhplus.concert.infrastructure.persistence.mapper.BalanceEntityMapper;
import com.hhplus.concert.infrastructure.persistence.mapper.BalanceHistoryEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class BalanceHistoryRepositoryImpl implements BalanceHistoryRepository {

    private final BalanceHistoryJpaRepository jpaRepository;

    @Override
    public void insertBalanceHistory(BalanceHistoryDomain balanceHistoryDomain) {
        jpaRepository.save(BalanceEntityMapper.domainToEntity(balanceHistoryDomain));
    }

    @Override
    public BalanceHistoryDomain findFirstByUserIdOrderByTransactionDateDesc(long userId) {
        return BalanceHistoryEntityMapper.entityToDomain(jpaRepository.findFirstByUserIdOrderByTransactionDateDesc(userId));
    }
}
