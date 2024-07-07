package com.hhplus.concert.infrastructure.persistence.implement;

import com.hhplus.concert.business.repository.BalanceHistoryRepository;
import com.hhplus.concert.infrastructure.persistence.dataaccess.BalanceHistoryJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class BalanceHistoryRepositoryImpl implements BalanceHistoryRepository {

    private final BalanceHistoryJpaRepository jpaRepository;

}
