package com.hhplus.concert.application.event;

import com.hhplus.concert.business.constant.BalanceTransactionType;

public class BalanceSuccessEvent {
    private final Long userId;
    private final Long amount;
    private final BalanceTransactionType transactionType;
    private final Long transactionDate;

    public BalanceSuccessEvent(Long userId, Long amount, BalanceTransactionType transactionType, Long transactionDate) {
        this.userId = userId;
        this.amount = amount;
        this.transactionType = transactionType;
        this.transactionDate = transactionDate;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getAmount() {
        return amount;
    }
    public BalanceTransactionType getTransactionType() {
        return transactionType;
    }
    public Long getTransactionDate() {
        return transactionDate;
    }

}
