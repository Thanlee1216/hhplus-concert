package com.hhplus.concert.application.event;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

// 이벤트 구독서비스
@Component
public class BalanceEventListener {
    private final DataPlatformSendService sendService;

    public BalanceEventListener(DataPlatformSendService sendService) {
        this.sendService = sendService;
    }
//     비동기로 이벤트 발행주체의 트랜잭션이 커밋된 후에 수행한다.
    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void balanceSuccessHandler(BalanceSuccessEvent event) {
        BalanceSuccessPayload payload = new BalanceSuccessPayload(event.getUserId(), event.getAmount(), event.getTransactionType(), event.getTransactionDate());
        sendService.insertBalanceHistory(payload);
    }
}
