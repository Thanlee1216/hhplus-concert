package com.hhplus.concert.presentation.controller;

import com.hhplus.concert.presentation.dto.request.PaymentRequestDTO;
import com.hhplus.concert.presentation.dto.response.PaymentHistoryResponseDTO;
import com.hhplus.concert.presentation.dto.response.PaymentResponseDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/payment")
public class PaymentApiController {

    /**
     * 결제 API
     * @param request
     * @param requestDTO
     * @return
     */
    @PostMapping("")
    public PaymentResponseDTO payment(HttpServletRequest request, @RequestBody PaymentRequestDTO requestDTO) {
        return new PaymentResponseDTO(0L, 0L, "");
    }

    /**
     * 결제 취소 API
     * @param request
     * @param requestDTO
     * @return
     */
    @PatchMapping("/cancel")
    public PaymentResponseDTO paymentCancel(HttpServletRequest request, @RequestBody PaymentRequestDTO requestDTO) {
        return new PaymentResponseDTO(0L, 0L, "");
    }

    /**
     * 결제/취소 내역 조회 API
     * @param request
     * @param user_id
     * @return
     */
    @GetMapping("/history/{user_id}")
    public List<PaymentHistoryResponseDTO> paymentHistory(HttpServletRequest request, @PathVariable Long user_id) {
        return List.of();
    }


}
