package com.hhplus.concert.business.domain;

public record UserDomain(
        Long userId,
        String customerId,
        String customerName,
        Long balance
) {

    public UserDomain withBalance(Long balance) {
        return new UserDomain(userId, customerId, customerName, balance);
    }
}
