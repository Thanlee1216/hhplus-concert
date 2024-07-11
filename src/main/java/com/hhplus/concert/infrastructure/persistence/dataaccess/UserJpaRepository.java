package com.hhplus.concert.infrastructure.persistence.dataaccess;

import com.hhplus.concert.infrastructure.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {
}
