package com.hhplus.concert.business.repository;

import com.hhplus.concert.business.constant.OutboxStatusType;
import com.hhplus.concert.business.domain.OutboxDomain;

import java.util.List;

public interface OutboxRepository {

    void insertMessage(OutboxDomain domain);

    List<OutboxDomain> findByOutboxStatus(OutboxStatusType outboxStatusType);

    void updateMessage(OutboxDomain outboxDomain);
}
