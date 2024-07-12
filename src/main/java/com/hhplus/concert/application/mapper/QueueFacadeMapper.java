package com.hhplus.concert.application.mapper;

import com.hhplus.concert.application.dto.QueueFacadeDTO;
import com.hhplus.concert.business.domain.QueueDomain;

public class QueueFacadeMapper {

    public static QueueDomain toQueueDomain(QueueFacadeDTO facadeDTO) {
        return new QueueDomain(facadeDTO.queueNumber(), facadeDTO.userId(), facadeDTO.createdAt(), facadeDTO.activeAt(), facadeDTO.status(), facadeDTO.userQueueCount());
    }

    public static QueueFacadeDTO toQueueFacadeDTO(QueueDomain queueDomain) {
        return new QueueFacadeDTO(queueDomain.queueNumber(), queueDomain.userId(), queueDomain.createdAt(), queueDomain.activeAt(), queueDomain.status(), queueDomain.userQueueCount());
    }
}
