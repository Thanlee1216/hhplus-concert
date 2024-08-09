package com.hhplus.concert.business.domain;

import com.hhplus.concert.business.constant.BalanceTransactionType;

public record BalanceHistoryDomain(
        Long userId
        , Long amount
        , BalanceTransactionType transactionType
        , Long transactionDate
) {
}
