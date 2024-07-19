package com.hhplus.concert.business.repository;

import com.hhplus.concert.business.domain.UserDomain;

public interface UserRepository {

    UserDomain findById(Long userId);

    UserDomain save(UserDomain userDomain);
}
