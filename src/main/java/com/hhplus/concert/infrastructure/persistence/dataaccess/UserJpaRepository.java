package com.hhplus.concert.infrastructure.persistence.dataaccess;

import com.hhplus.concert.infrastructure.entity.UsersEntity;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<UsersEntity, Long> {
    @Override
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<UsersEntity> findById(Long aLong);
}
