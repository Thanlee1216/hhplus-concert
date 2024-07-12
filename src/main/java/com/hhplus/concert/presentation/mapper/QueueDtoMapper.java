package com.hhplus.concert.presentation.mapper;

import com.hhplus.concert.application.dto.QueueFacadeDTO;
import com.hhplus.concert.business.domain.QueueDomain;
import com.hhplus.concert.presentation.dto.queue.request.QueueRequestDTO;
import com.hhplus.concert.presentation.dto.queue.response.QueueResponseDTO;

public class QueueDtoMapper {

    public static QueueFacadeDTO toQueueFacadeDTO(QueueRequestDTO requestDto) {
        return new QueueFacadeDTO(requestDto.queueNumber(), requestDto.userId(), null, null, null, null);
    }

    public static QueueResponseDTO toQueueResponseDTO(QueueFacadeDTO queueFacadeDTO) {
        return new QueueResponseDTO(queueFacadeDTO.userId(), queueFacadeDTO.queueNumber(), queueFacadeDTO.status(), queueFacadeDTO.userQueueCount());
    }
}
