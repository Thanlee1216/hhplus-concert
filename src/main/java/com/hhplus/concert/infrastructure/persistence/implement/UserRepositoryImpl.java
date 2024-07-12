package com.hhplus.concert.infrastructure.persistence.implement;

import com.hhplus.concert.business.domain.UserDomain;
import com.hhplus.concert.business.repository.UserRepository;
import com.hhplus.concert.infrastructure.persistence.dataaccess.UserJpaRepository;
import com.hhplus.concert.infrastructure.persistence.mapper.UserEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class UserRepositoryImpl implements UserRepository {

    private final UserJpaRepository jpaRepository;

    @Override
    public UserDomain findById(Long userId) {
        return UserEntityMapper.toUserDomain(jpaRepository.findById(userId));
    }

    @Override
    public UserDomain save(UserDomain userDomain) {
        return UserEntityMapper.toUserDomain(jpaRepository.save(UserEntityMapper.toUserEntity(userDomain)));
    }
}
