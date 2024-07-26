package com.hhplus.concert.interfaces.presentation.controller;

import com.hhplus.concert.application.facade.ConcertFacade;
import com.hhplus.concert.interfaces.presentation.dto.concert.request.ConcertReservationRequestDTO;
import com.hhplus.concert.interfaces.presentation.dto.concert.response.ConcertResponseDTO;
import com.hhplus.concert.interfaces.presentation.dto.concert.response.ConcertSeatResponseDTO;
import com.hhplus.concert.interfaces.presentation.mapper.ConcertDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/concert")
public class ConcertApiController {

    @Autowired
    private ConcertFacade concertFacade;

    /**
     * 예약 가능한 날짜를 조회하는 API
     * @param concertId
     * @return
     */
    @GetMapping("/{concert_id}")
    public List<ConcertResponseDTO> getDateOfConcertId(@PathVariable("concert_id") Long concertId) {
        return ConcertDtoMapper.toConcertResponseDtoList(concertFacade.getDateOfConcertId(concertId));
    }

    /**
     * 예약 가능한 좌석을 조회하는 API
     * @param concertId
     * @param concertOptionId
     * @return
     */
    @GetMapping("/{concert_id}/{concert_option_id}")
    public List<ConcertSeatResponseDTO> getSeatOfConcertIdAndConcertOptionId(@PathVariable("concert_id") Long concertId, @PathVariable("concert_option_id") Long concertOptionId) {
        return ConcertDtoMapper.toConcertSeatResponseDtoList(concertFacade.getSeatOfConcertIdAndConcertOptionId(concertId, concertOptionId));
    }

    /**
     * 특정 좌석을 예약하는 API
     * @param requestDTO
     * @return
     */
    @PutMapping("/reservation")
    public ConcertSeatResponseDTO seatReservation(@RequestBody ConcertReservationRequestDTO requestDTO) throws Exception{
        return ConcertDtoMapper.toConcertSeatResponseDTO(concertFacade.seatReservation(ConcertDtoMapper.toConcertFacadeDTO(requestDTO)));
    }
}
