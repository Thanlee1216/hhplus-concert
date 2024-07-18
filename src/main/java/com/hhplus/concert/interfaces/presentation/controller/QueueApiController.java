package com.hhplus.concert.interfaces.presentation.controller;

import com.hhplus.concert.application.facade.QueueFacade;
import com.hhplus.concert.interfaces.presentation.dto.queue.request.QueueRequestDTO;
import com.hhplus.concert.interfaces.presentation.dto.queue.response.QueueResponseDTO;
import com.hhplus.concert.interfaces.presentation.mapper.QueueDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QueueApiController {

    @Autowired
    QueueFacade queueFacade;

    @PutMapping("/queue")
    public QueueResponseDTO queueEntry(@RequestBody QueueRequestDTO requestDTO) throws Exception {
        return QueueDtoMapper.toQueueResponseDTO(queueFacade.queueEntry(QueueDtoMapper.toQueueFacadeDTO(requestDTO)));
    }
}
