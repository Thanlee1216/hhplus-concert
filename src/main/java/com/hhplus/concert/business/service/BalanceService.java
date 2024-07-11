package com.hhplus.concert.business.service;

import com.hhplus.concert.business.domain.UserDomain;
import com.hhplus.concert.business.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BalanceService {

    @Autowired
    private UserRepository userRepository;

    public UserDomain findBalanceById(Long userId) {
        return userRepository.findById(userId);
    }

    public UserDomain updateBalance(UserDomain userDomain) {
        return userRepository.save(userDomain);
    }
}
