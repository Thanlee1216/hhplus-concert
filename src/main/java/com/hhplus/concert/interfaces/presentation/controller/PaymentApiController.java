package com.hhplus.concert.interfaces.presentation.controller;

import com.hhplus.concert.application.facade.PaymentFacade;
import com.hhplus.concert.interfaces.presentation.dto.payment.request.PaymentRequestDTO;
import com.hhplus.concert.interfaces.presentation.dto.payment.response.PaymentResponseDTO;
import com.hhplus.concert.interfaces.presentation.mapper.PaymentDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/payment")
public class PaymentApiController {

    @Autowired
    private PaymentFacade paymentFacade;

    /**
     * 결제 API
     * @param requestDTO
     * @return
     */
    @PostMapping("")
    public PaymentResponseDTO payment(@RequestBody PaymentRequestDTO requestDTO) {
        return PaymentDtoMapper.toPaymentResponseDTO(paymentFacade.payment(PaymentDtoMapper.toPaymentFacadeDTO(requestDTO)));
    }

}
