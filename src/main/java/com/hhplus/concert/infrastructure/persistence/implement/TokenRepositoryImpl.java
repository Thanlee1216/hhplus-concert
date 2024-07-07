package com.hhplus.concert.infrastructure.persistence.implement;

import com.hhplus.concert.business.repository.TokenRepository;
import com.hhplus.concert.infrastructure.persistence.dataaccess.TokenJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class TokenRepositoryImpl implements TokenRepository {

    private final TokenJpaRepository jpaRepository;

}
