package com.hhplus.concert.infrastructure.persistence.mapper;

import com.hhplus.concert.business.domain.BalanceHistoryDomain;
import com.hhplus.concert.infrastructure.entity.BalanceHistoryEntity;

public class BalanceHistoryEntityMapper {

    public static BalanceHistoryDomain entityToDomain(BalanceHistoryEntity entity) {
        return new BalanceHistoryDomain(entity.getUserId(), entity.getAmount(), entity.getTransactionType(), entity.getTransactionDate());
    }
}
