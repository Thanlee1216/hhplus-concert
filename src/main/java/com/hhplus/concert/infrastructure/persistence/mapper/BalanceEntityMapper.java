package com.hhplus.concert.infrastructure.persistence.mapper;

import com.hhplus.concert.business.domain.BalanceHistoryDomain;
import com.hhplus.concert.infrastructure.entity.BalanceHistoryEntity;

public class BalanceEntityMapper {

    public static BalanceHistoryEntity domainToEntity(BalanceHistoryDomain domain) {
        return new BalanceHistoryEntity(domain.userId(), domain.amount(), domain.transactionType(), domain.transactionDate());
    }
}
