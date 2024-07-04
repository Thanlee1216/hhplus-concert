package com.hhplus.concert.presentation.controller;

import com.hhplus.concert.presentation.dto.request.BalanceRequestDTO;
import com.hhplus.concert.presentation.dto.response.BalanceHistoryResponseDTO;
import com.hhplus.concert.presentation.dto.response.BalanceResponseDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/balance")
public class BalanceApiController {

    /**
     * 유저의 포인트를 조회하기 위한 API
     * @param request
     * @param user_id
     * @return
     */
    @GetMapping("/{user_id}")
    public BalanceResponseDTO findBalanceById(HttpServletRequest request, @PathVariable("user_id") Long user_id) {
        return new BalanceResponseDTO(0L, 0L);
    }

    /**
     * 유저의 포인트를 충전하기 위한 API
     * @param request
     * @param requestDTO
     * @return
     */
    @PatchMapping("/charge")
    public BalanceResponseDTO chargeBalance(HttpServletRequest request, @RequestBody BalanceRequestDTO requestDTO) {
        return new BalanceResponseDTO(0L, 0L);
    }

    /**
     * 유저의 포인트 충전/사용 내역 조회를 위한 API
     * @param user_id
     * @return
     */
    @GetMapping("/history/{user_id}")
    public List<BalanceHistoryResponseDTO> balanceHistory(@PathVariable Long user_id) {
        return List.of();
    }
}
