package com.hhplus.concert.business.service;

import com.hhplus.concert.business.constant.OutboxStatusType;
import com.hhplus.concert.business.domain.OutboxDomain;
import com.hhplus.concert.business.repository.OutboxRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OutboxService {

    private final OutboxRepository outboxRepository;

    public void insertOutbox(OutboxDomain domain) {
        outboxRepository.insertMessage(domain);
    }

    public List<OutboxDomain> getInitMessage() {
        return outboxRepository.findByOutboxStatus(OutboxStatusType.INIT);
    }

    public void updateMessage(OutboxDomain outboxDomain) {
        outboxRepository.updateMessage(outboxDomain);
    }
}
