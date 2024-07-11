package com.hhplus.concert.infrastructure.persistence.implement;

import com.hhplus.concert.business.repository.UserQueueRepository;
import com.hhplus.concert.infrastructure.persistence.dataaccess.UserQueueJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class UserQueueRepositoryImpl implements UserQueueRepository {

    private final UserQueueJpaRepository jpaRepository;

}
