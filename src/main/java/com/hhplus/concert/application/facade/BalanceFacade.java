package com.hhplus.concert.application.facade;

import com.hhplus.concert.application.dto.BalanceFacadeDTO;
import com.hhplus.concert.application.mapper.BalanceFacadeMapper;
import com.hhplus.concert.business.constant.BalanceTransactionType;
import com.hhplus.concert.business.domain.UserDomain;
import com.hhplus.concert.business.service.BalanceService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BalanceFacade {

    @Autowired
    private BalanceService balanceService;

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
    public BalanceFacadeDTO chargeBalance(BalanceFacadeDTO balanceFacadeDTO) {
        UserDomain userDomain = balanceService.findBalanceById(balanceFacadeDTO.userId());
        return BalanceFacadeMapper.toBalanceFacadeDTO(balanceService.updateBalance(userDomain.withBalance(userDomain.balance() + balanceFacadeDTO.amount())));
    }
}
