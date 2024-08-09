package com.hhplus.concert.business.repository;

import com.hhplus.concert.business.domain.BalanceHistoryDomain;

public interface BalanceHistoryRepository {

    void insertBalanceHistory(BalanceHistoryDomain balanceHistoryDomain);
}
