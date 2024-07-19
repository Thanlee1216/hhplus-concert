package com.hhplus.concert.application.mapper;

import com.hhplus.concert.application.dto.BalanceFacadeDTO;
import com.hhplus.concert.business.domain.BalanceDomain;
import com.hhplus.concert.business.domain.UserDomain;

public class BalanceFacadeMapper {

    public static BalanceFacadeDTO toBalanceFacadeDTO(UserDomain userDomain) {
        return new BalanceFacadeDTO(userDomain.userId(), userDomain.balance());
    }

    public static BalanceDomain toBalanceDomain(BalanceFacadeDTO balanceFacadeDTO) {
        return new BalanceDomain(balanceFacadeDTO.userId(), balanceFacadeDTO.amount(), null);
    }
}
