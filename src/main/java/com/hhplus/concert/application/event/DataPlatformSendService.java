package com.hhplus.concert.application.event;

import com.hhplus.concert.business.domain.BalanceHistoryDomain;
import com.hhplus.concert.business.service.BalanceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class DataPlatformSendService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private BalanceService balanceService;

    public void insertBalanceHistory(BalanceSuccessPayload payload) {
        try {
            balanceService.insertBalanceHistory(new BalanceHistoryDomain(payload.userId(), payload.amount(), payload.transactionType(), payload.transactionDate()));
        } catch (Exception e) {
            logger.error("*********************");
            logger.error("포인트 History 처리 실패");
            logger.error("*********************");
        }
    }
}
