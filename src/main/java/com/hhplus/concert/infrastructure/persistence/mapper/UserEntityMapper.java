package com.hhplus.concert.infrastructure.persistence.mapper;

import com.hhplus.concert.business.domain.UserDomain;
import com.hhplus.concert.infrastructure.entity.UsersEntity;

import java.util.Optional;

public class UserEntityMapper {

    public static UserDomain toUserDomain(Optional<UsersEntity> userEntity) {
        return new UserDomain(userEntity.get().getUserId(), userEntity.get().getCustomerId(), userEntity.get().getCustomerName(), userEntity.get().getBalance());
    }

    public static UserDomain toUserDomain(UsersEntity userEntity) {
        return new UserDomain(userEntity.getUserId(), userEntity.getCustomerId(), userEntity.getCustomerName(), userEntity.getBalance());
    }

    public static UsersEntity toUserEntity(UserDomain userDomain) {
        return new UsersEntity(userDomain.userId(), userDomain.customerId(), userDomain.customerName(), userDomain.balance());
    }
}
