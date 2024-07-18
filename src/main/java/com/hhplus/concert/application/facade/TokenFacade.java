package com.hhplus.concert.application.facade;

import com.hhplus.concert.business.domain.UserDomain;
import com.hhplus.concert.business.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TokenFacade {

    @Autowired
    TokenService tokenService;

    public boolean validateToken(String token) {
        return tokenService.validateToken(token);
    }
}
