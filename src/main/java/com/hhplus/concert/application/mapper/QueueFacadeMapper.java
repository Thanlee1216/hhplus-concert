package com.hhplus.concert.application.mapper;

import com.hhplus.concert.application.dto.QueueFacadeDTO;
import com.hhplus.concert.business.domain.QueueDomain;

public class QueueFacadeMapper {

    public static QueueDomain toQueueDomain(QueueFacadeDTO facadeDTO) {
        return new QueueDomain(facadeDTO.userId(), facadeDTO.userQueueCount(), facadeDTO.token());
    }

    public static QueueFacadeDTO toQueueFacadeDTO(QueueDomain queueDomain) {
        return new QueueFacadeDTO(queueDomain.userId(), queueDomain.userQueueCount(), queueDomain.token());
    }
}
