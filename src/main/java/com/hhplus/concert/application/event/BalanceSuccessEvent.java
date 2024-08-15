package com.hhplus.concert.application.event;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.hhplus.concert.business.constant.BalanceTransactionType;
import lombok.Data;

@Data
public class BalanceSuccessEvent {

    private Long outboxSeq;
    private Long userId;
    private Long amount;
    private BalanceTransactionType transactionType;
    private Long transactionDate;

    @JsonCreator
    public BalanceSuccessEvent(
            @JsonProperty("outboxSeq") Long outboxSeq,
            @JsonProperty("userId") Long userId,
            @JsonProperty("amount") Long amount,
            @JsonProperty("transactionType") BalanceTransactionType transactionType,
            @JsonProperty("transactionDate") Long transactionDate) {
        this.outboxSeq = outboxSeq;
        this.userId = userId;
        this.amount = amount;
        this.transactionType = transactionType;
        this.transactionDate = transactionDate;
    }

}
