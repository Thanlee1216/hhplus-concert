package com.hhplus.concert.application.event;

import com.hhplus.concert.business.constant.BalanceTransactionType;

public record BalanceSuccessPayload(
        Long userId
        , Long amount
        , BalanceTransactionType transactionType
        , Long transactionDate
) {
}
