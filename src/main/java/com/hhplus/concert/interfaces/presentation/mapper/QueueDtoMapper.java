package com.hhplus.concert.interfaces.presentation.mapper;

import com.hhplus.concert.application.dto.QueueFacadeDTO;
import com.hhplus.concert.interfaces.presentation.dto.queue.request.QueueRequestDTO;
import com.hhplus.concert.interfaces.presentation.dto.queue.response.QueueResponseDTO;

public class QueueDtoMapper {

    public static QueueFacadeDTO toQueueFacadeDTO(QueueRequestDTO requestDto) {
        return new QueueFacadeDTO(requestDto.userId(), null, requestDto.token());
    }

    public static QueueResponseDTO toQueueResponseDTO(QueueFacadeDTO queueFacadeDTO) {
        return new QueueResponseDTO(queueFacadeDTO.userId(), queueFacadeDTO.userQueueCount(), queueFacadeDTO.token());
    }
}
