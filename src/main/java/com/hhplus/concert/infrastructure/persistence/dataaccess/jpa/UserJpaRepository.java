package com.hhplus.concert.infrastructure.persistence.dataaccess.jpa;

import com.hhplus.concert.infrastructure.entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<UsersEntity, Long> {
}
