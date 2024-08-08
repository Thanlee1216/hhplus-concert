package com.hhplus.concert.business.config;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

// 이벤트 구독서비스
@Component
public class PaymentEventListener {
//    private final DataPlatformSendService sendService;

//    public PaymentEventListener(DataPlatformSendService sendService) {
//        this.sendService = sendService;
//    }
    // 비동기로 이벤트 발행주체의 트랜잭션이 커밋된 후에 수행한다.
//    @Async
//    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
//    public void paymentSuccessHandler(PaymentSuccessEvent event) {
//        // (4) 주문 정보 전달
//        PaymentSuccessPayload payload = new PaymentSuccessPayload(event);
//        sendService.send(payload);
//    }
}
