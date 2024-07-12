package com.hhplus.concert.presentation.controller;

import com.hhplus.concert.application.facade.BalanceFacade;
import com.hhplus.concert.presentation.dto.balance.request.BalanceRequestDTO;
import com.hhplus.concert.presentation.dto.balance.response.BalanceResponseDTO;
import com.hhplus.concert.presentation.mapper.BalanceDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/balance")
public class BalanceApiController {

    @Autowired
    private BalanceFacade balanceFacade;

    /**
     * 유저의 포인트를 조회하기 위한 API
     * @param request
     * @param user_id
     * @return
     */
    @GetMapping("/{user_id}")
    public BalanceResponseDTO findBalanceById(@PathVariable("user_id") Long userId) {
        return BalanceDtoMapper.toBalanceResponseDTO(balanceFacade.findBalanceById(userId));
    }

    /**
     * 유저의 포인트를 충전하기 위한 API
     * @param request
     * @param requestDTO
     * @return
     */
    @PatchMapping("/charge")
    public BalanceResponseDTO chargeBalance(@RequestBody BalanceRequestDTO requestDTO) {
        return BalanceDtoMapper.toBalanceResponseDTO(balanceFacade.chargeBalance(BalanceDtoMapper.toBalanceFacadeDTO(requestDTO)));
    }

}
