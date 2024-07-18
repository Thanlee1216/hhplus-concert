package com.hhplus.concert.business.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hhplus.concert.business.domain.UserDomain;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private final Long expirationTime = 900000L; // 15분


    @Autowired
    ObjectMapper mapper;

    public String createToken(UserDomain userDomain) throws Exception {
        Claims claims = Jwts.claims().setSubject(userDomain.customerId()).build();
        Date now = new Date();
        long tokenValidMilisecond = expirationTime;
        JwtBuilder jb = Jwts.builder();
        String token = jb
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + tokenValidMilisecond))
                .signWith(key)
                .compact();
        return token;
    }

    public boolean validateToken(String jwtToken) {
        try {
            Jwts.parser().setSigningKey(key).build().parseClaimsJws(jwtToken);
            return true;
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT signature.");
            logger.trace("Invalid JWT signature trace: {}", e);
            throw e;
        } catch (ExpiredJwtException e) {
            logger.error("Expired JWT token.");//JWT를 생성할 때 지정한 유효기간 초과할 때.
            logger.trace("Expired JWT token trace: {}", e);
            throw e;
        } catch (UnsupportedJwtException e) {
            logger.error("Unsupported JWT token.");//예상하는 형식과 일치하지 않는 특정 형식이나 구성의 JWT일 때
            logger.trace("Unsupported JWT token trace: {}", e);
            throw e;
        } catch (IllegalArgumentException e) {
            logger.error("JWT token compact of handler are invalid.");
            logger.trace("JWT token compact of handler are invalid trace: {}", e);
            throw e;
        } catch (Exception e) {
            logger.error("Exception.");
            logger.trace("Exception trace: {}", e);
            throw e;
        }
    }
}
