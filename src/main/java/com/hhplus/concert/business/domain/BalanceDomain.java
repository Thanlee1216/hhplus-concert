package com.hhplus.concert.business.domain;

import com.hhplus.concert.business.constant.BalanceTransactionType;

public record BalanceDomain(
        Long userId,
        Long amount,
        BalanceTransactionType transactionType
) {

    public BalanceDomain withTransactionType(BalanceTransactionType transactionType) {
        return new BalanceDomain(userId, amount, transactionType);
    }
}
