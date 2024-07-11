package com.hhplus.concert.infrastructure.persistence.implement;

import com.hhplus.concert.business.repository.PaymentHistoryRepository;
import com.hhplus.concert.infrastructure.persistence.dataaccess.PaymentHistoryJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class PaymentHistoryRepositoryImpl implements PaymentHistoryRepository {

    private final PaymentHistoryJpaRepository jpaRepository;

}
