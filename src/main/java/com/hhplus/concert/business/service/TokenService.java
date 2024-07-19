package com.hhplus.concert.business.service;

import com.hhplus.concert.business.domain.UserDomain;
import com.hhplus.concert.business.util.JwtTokenProvider;
import com.hhplus.concert.global.exception.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    public String createToken(UserDomain userDomain) throws Exception {
        return jwtTokenProvider.createToken(userDomain);
    }

    public boolean validateToken(String token) {
        try {
            return jwtTokenProvider.validateToken(token);
        } catch (Exception e) {
            throw new JwtException("500", e.getMessage());
        }
    }

}
