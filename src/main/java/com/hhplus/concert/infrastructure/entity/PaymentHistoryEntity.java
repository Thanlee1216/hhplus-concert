package com.hhplus.concert.infrastructure.entity;

import com.hhplus.concert.global.constant.PaymentTransactionType;
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
}
