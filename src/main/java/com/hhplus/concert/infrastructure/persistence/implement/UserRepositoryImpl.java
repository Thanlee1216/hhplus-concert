package com.hhplus.concert.infrastructure.persistence.implement;

import com.hhplus.concert.business.repository.UserRepository;
import com.hhplus.concert.infrastructure.persistence.dataaccess.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class UserRepositoryImpl implements UserRepository {

    private final UserJpaRepository jpaRepository;

}
