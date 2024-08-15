package com.hhplus.concert.application.facade;

import com.hhplus.concert.application.dto.OutboxFacadeDTO;
import com.hhplus.concert.application.mapper.OutboxFacadeMapper;
import com.hhplus.concert.business.service.OutboxService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class OutboxFacade {

    private final OutboxService outboxService;

    public List<OutboxFacadeDTO> getInitMessage() {
        return OutboxFacadeMapper.toOutboxFacadeDTOList(outboxService.getInitMessage());
    }
}
