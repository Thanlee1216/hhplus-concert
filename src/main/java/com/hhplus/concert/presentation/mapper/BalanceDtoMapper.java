package com.hhplus.concert.presentation.mapper;

import com.hhplus.concert.application.dto.BalanceFacadeDTO;
import com.hhplus.concert.presentation.dto.balance.request.BalanceRequestDTO;
import com.hhplus.concert.presentation.dto.balance.response.BalanceResponseDTO;

public class BalanceDtoMapper {

    public static BalanceFacadeDTO toBalanceFacadeDTO(BalanceRequestDTO requestDTO) {
        return new BalanceFacadeDTO(requestDTO.user_id(), requestDTO.amount());
    }

    public static BalanceResponseDTO toBalanceResponseDTO(BalanceFacadeDTO facadeDTO) {
        return new BalanceResponseDTO(facadeDTO.userId(), facadeDTO.amount());
    }
}
