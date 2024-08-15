package com.hhplus.concert.application.facade;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hhplus.concert.application.dto.BalanceFacadeDTO;
import com.hhplus.concert.application.event.BalanceSuccessEvent;
import com.hhplus.concert.application.mapper.BalanceFacadeMapper;
import com.hhplus.concert.business.constant.BalanceTransactionType;
import com.hhplus.concert.business.constant.OutboxStatusType;
import com.hhplus.concert.business.domain.OutboxDomain;
import com.hhplus.concert.business.domain.UserDomain;
import com.hhplus.concert.business.service.BalanceService;
import com.hhplus.concert.business.service.OutboxService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BalanceFacade {


    private final BalanceService balanceService;

    private final OutboxService outboxService;

    private final ObjectMapper objectMapper;

    /**
     * 포인트 조회
     * @param userId
     * @return
     */
    @Transactional
    public BalanceFacadeDTO findBalanceById(Long userId) {
        return BalanceFacadeMapper.toBalanceFacadeDTO(balanceService.findBalanceById(userId));
    }

    /**
     * 포인트 충전
     * @param balanceFacadeDTO
     * @return
     */
    @Transactional
    public BalanceFacadeDTO chargeBalance(BalanceFacadeDTO balanceFacadeDTO) throws Exception{
        UserDomain userDomain = balanceService.findBalanceById(balanceFacadeDTO.userId());
        BalanceFacadeDTO resultDto = BalanceFacadeMapper.toBalanceFacadeDTO(balanceService.updateBalance(userDomain.withBalance(userDomain.balance() + balanceFacadeDTO.amount())));
        BalanceSuccessEvent balanceSuccessEvent = new BalanceSuccessEvent(null, resultDto.userId(), balanceFacadeDTO.amount(), BalanceTransactionType.CHARGE, System.currentTimeMillis());
        OutboxDomain outboxDomain = new OutboxDomain(null, "balance-success", resultDto.userId().toString(), objectMapper.writeValueAsString(balanceSuccessEvent), OutboxStatusType.INIT);
        outboxService.insertOutbox(outboxDomain);
        return resultDto;
    }
}
