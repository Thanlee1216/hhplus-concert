package com.hhplus.concert.business.domain;

import com.hhplus.concert.business.constant.PaymentTransactionType;

import java.sql.Timestamp;

public record PaymentHistoryDomain(
        Long paymentHistoryId,
        Long userId,
        Long ticketId,
        Long amount,
        PaymentTransactionType transactionType,
        Timestamp transactionDate
) {
    public static PaymentHistoryDomain of(TicketDomain ticketDomain) {
        return new PaymentHistoryDomain(null, ticketDomain.userId(), ticketDomain.ticketId(), ticketDomain.seatPrice(), null, null);
    }

    public PaymentHistoryDomain withTransaction(PaymentTransactionType transactionType, Timestamp transactionDate) {
        return new PaymentHistoryDomain(paymentHistoryId, userId, ticketId, amount, transactionType, transactionDate);
    }
}
