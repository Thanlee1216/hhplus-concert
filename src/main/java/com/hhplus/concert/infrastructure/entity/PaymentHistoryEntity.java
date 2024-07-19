package com.hhplus.concert.infrastructure.entity;

import com.hhplus.concert.business.constant.PaymentTransactionType;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Entity
@Table(name = "payment_history")
@Data
public class PaymentHistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_history_id")
    private Long paymentHistoryId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "ticket_id")
    private Long ticketId;

    @Column(name = "amount")
    private Long amount;

    @Column(name = "transaction_type")
    @Enumerated(EnumType.STRING)
    private PaymentTransactionType transactionType;

    @Column(name = "transaction_date")
    private Timestamp transactionDate;

    public PaymentHistoryEntity() {}
    public PaymentHistoryEntity(Long paymentHistoryId, Long userId, Long ticketId, Long amount, PaymentTransactionType transactionType, Timestamp transactionDate) {
        this.paymentHistoryId = paymentHistoryId;
        this.userId = userId;
        this.ticketId = ticketId;
        this.amount = amount;
        this.transactionType = transactionType;
        this.transactionDate = transactionDate;
    }
}
