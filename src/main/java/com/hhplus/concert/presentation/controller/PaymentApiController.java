package com.hhplus.concert.presentation.controller;

import com.hhplus.concert.application.facade.PaymentFacade;
import com.hhplus.concert.presentation.dto.payment.request.PaymentRequestDTO;
import com.hhplus.concert.presentation.dto.payment.response.PaymentHistoryResponseDTO;
import com.hhplus.concert.presentation.dto.payment.response.PaymentResponseDTO;
import com.hhplus.concert.presentation.mapper.PaymentDtoMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
