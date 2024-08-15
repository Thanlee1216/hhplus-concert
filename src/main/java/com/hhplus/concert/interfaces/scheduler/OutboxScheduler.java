package com.hhplus.concert.interfaces.scheduler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hhplus.concert.application.dto.OutboxFacadeDTO;
import com.hhplus.concert.application.event.BalanceEventPublisher;
import com.hhplus.concert.application.event.BalanceSuccessEvent;
import com.hhplus.concert.application.facade.OutboxFacade;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class OutboxScheduler {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final OutboxFacade outboxFacade;

    private final BalanceEventPublisher balanceEventPublisher;

    private final ObjectMapper objectMapper;

    @Scheduled(fixedRate = 5000)
    public void outboxChecker() {
        List<OutboxFacadeDTO> outboxFacadeDTO = outboxFacade.getInitMessage();
        outboxFacadeDTO.stream().forEach(dto -> {
            BalanceSuccessEvent balanceSuccessEvent = null;
            try {
                balanceSuccessEvent = objectMapper.readValue(dto.payload(), BalanceSuccessEvent.class);
            } catch (JsonProcessingException e) {
                logger.error("BalanceSuccessEvent parsing error", e);
            }
            balanceSuccessEvent.setOutboxSeq(dto.outboxSeq());
            try {
                balanceEventPublisher.success(balanceSuccessEvent);
            }catch (Exception e){
                logger.error("BalanceSuccessEvent publisher error", e);
            }
        });
    }
}
