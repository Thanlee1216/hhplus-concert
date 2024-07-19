package com.hhplus.concert.infrastructure.persistence.mapper;

import com.hhplus.concert.business.domain.PaymentHistoryDomain;
import com.hhplus.concert.infrastructure.entity.PaymentHistoryEntity;

public class PaymentHistoryEntityMapper {

    public static PaymentHistoryEntity toPaymentHistoryEntity(PaymentHistoryDomain paymentHistoryDomain) {
        return new PaymentHistoryEntity(paymentHistoryDomain.paymentHistoryId()
                , paymentHistoryDomain.userId()
                , paymentHistoryDomain.ticketId()
                , paymentHistoryDomain.amount()
                , paymentHistoryDomain.transactionType()
                , paymentHistoryDomain.transactionDate()
        );
    }

    public static PaymentHistoryDomain toPaymentHistoryDomain(PaymentHistoryEntity paymentHistoryEntity) {
        return new PaymentHistoryDomain(paymentHistoryEntity.getPaymentHistoryId()
                , paymentHistoryEntity.getUserId()
                , paymentHistoryEntity.getTicketId()
                , paymentHistoryEntity.getAmount()
                , paymentHistoryEntity.getTransactionType()
                , paymentHistoryEntity.getTransactionDate()
        );
    }
}
