package com.hhplus.concert.interfaces.scheduled;

import com.hhplus.concert.business.service.QueueService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class QueueScheduled {

    @Autowired
    QueueService queueService;

    @Scheduled(fixedDelay = 30000)
    @Transactional
    public void expiredQueue() {
        queueService.expiredQueue();
    }
}
