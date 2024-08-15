package com.hhplus.concert.application.mapper;

import com.hhplus.concert.application.dto.OutboxFacadeDTO;
import com.hhplus.concert.business.domain.OutboxDomain;

import java.util.List;

public class OutboxFacadeMapper {

    public static List<OutboxFacadeDTO> toOutboxFacadeDTOList(List<OutboxDomain> outboxDomainList) {
        return outboxDomainList.stream()
                .map(domain -> new OutboxFacadeDTO(
                        domain.outboxSeq()
                        , domain.topic()
                        , domain.topicKey()
                        , domain.payload()
                        , domain.outboxStatus()
                    )
                ).toList();
    }

    public static OutboxDomain toDomain(OutboxFacadeDTO outboxFacadeDTO) {
        return new OutboxDomain(outboxFacadeDTO.outboxSeq(), outboxFacadeDTO.topic(), outboxFacadeDTO.topicKay(), outboxFacadeDTO.payload(), outboxFacadeDTO.outboxStatus());
    }
}
