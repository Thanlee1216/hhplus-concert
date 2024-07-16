package com.hhplus.concert.interfaces.presentation.mapper;

import com.hhplus.concert.application.dto.PaymentFacadeDTO;
import com.hhplus.concert.interfaces.presentation.dto.payment.request.PaymentRequestDTO;
import com.hhplus.concert.interfaces.presentation.dto.payment.response.PaymentResponseDTO;

public class PaymentDtoMapper {

    public static PaymentFacadeDTO toPaymentFacadeDTO(PaymentRequestDTO requestDTO) {
        return new PaymentFacadeDTO(requestDTO.userId(), requestDTO.seatId(), null);
    }

    public static PaymentResponseDTO toPaymentResponseDTO(PaymentFacadeDTO facadeDTO) {
        return new PaymentResponseDTO(facadeDTO.userId(), facadeDTO.seatId(), null);
    }
}
