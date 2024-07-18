package com.hhplus.concert.business.repository;

import com.hhplus.concert.business.domain.PaymentHistoryDomain;

public interface PaymentHistoryRepository {
    PaymentHistoryDomain insertHistory(PaymentHistoryDomain paymentHistoryDomain);
}
