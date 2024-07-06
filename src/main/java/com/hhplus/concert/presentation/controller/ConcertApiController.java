package com.hhplus.concert.presentation.controller;

import com.hhplus.concert.presentation.dto.request.ConcertRequestDTO;
import com.hhplus.concert.presentation.dto.request.ConcertReservationRequestDTO;
import com.hhplus.concert.presentation.dto.response.ConcertResponseDTO;
import com.hhplus.concert.presentation.dto.response.ConcertSeatResponseDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/concert")
public class ConcertApiController {

    /**
     * 모든 콘서트의 목록을 조회하는 API
     * @return
     */
    @GetMapping("/list")
    public List<ConcertResponseDTO> allList() {
        return List.of();
    }

    /**
     * 특정 날짜의 콘서트 목록을 조회하는 API
     * @param concert_date
     * @return
     */
    @GetMapping("/list/{concert_date}")
    public List<ConcertResponseDTO> listOfDate(@PathVariable String concert_date) {
        return List.of();
    }

    /**
     * 특정 콘서트의 좌석 목록을 조회하는 API
     * @param requestDTO
     * @return
     */
    @GetMapping("/seat")
    public List<ConcertSeatResponseDTO> seatOfConcert(@RequestBody ConcertRequestDTO requestDTO) {
        return List.of();
    }

    /**
     * 특정 좌석을 예약하는 API
     * @param requestDTO
     * @return
     */
    @PatchMapping("/seat/reservation")
    public ConcertSeatResponseDTO seatReservation(@RequestBody ConcertReservationRequestDTO requestDTO) {
        return new ConcertSeatResponseDTO(0L, "", 0L, "");
    }
}
