package com.hhplus.concert.infrastructure.entity;

import com.hhplus.concert.business.constant.BalanceTransactionType;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Entity
@Table(name = "balance_history")
@Data
public class BalanceHistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "balance_history_id")
    private Long balanceHistoryId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "amount")
    private Long amount;

    @Column(name = "transaction_type")
    @Enumerated(EnumType.STRING)
    private BalanceTransactionType transactionType;

    @Column(name = "transaction_date")
    private Long transactionDate;

    public BalanceHistoryEntity() {}
    public BalanceHistoryEntity(Long userId, Long amount, BalanceTransactionType transactionType, Long transactionDate) {
        this.userId = userId;
        this.amount = amount;
        this.transactionType = transactionType;
        this.transactionDate = transactionDate;
    }
}
