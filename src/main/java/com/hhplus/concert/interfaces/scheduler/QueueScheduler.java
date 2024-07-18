package com.hhplus.concert.interfaces.scheduler;

import com.hhplus.concert.application.facade.QueueFacade;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class QueueScheduler {

    @Autowired
    QueueFacade queueFacade;

    @Scheduled(fixedDelay = 30000)
    @Transactional
    public void expiredQueue() {
        queueFacade.expiredQueue();
    }
}
