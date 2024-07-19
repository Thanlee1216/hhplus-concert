package com.hhplus.concert.interfaces.presentation.mapper;

import com.hhplus.concert.application.dto.ConcertFacadeDTO;
import com.hhplus.concert.interfaces.presentation.dto.concert.request.ConcertReservationRequestDTO;
import com.hhplus.concert.interfaces.presentation.dto.concert.response.ConcertResponseDTO;
import com.hhplus.concert.interfaces.presentation.dto.concert.response.ConcertSeatResponseDTO;

import java.util.List;

public class ConcertDtoMapper {

    public static ConcertFacadeDTO toConcertFacadeDTO(ConcertReservationRequestDTO requestDTO) {
        return new ConcertFacadeDTO(requestDTO.userId(), requestDTO.concertId(), null, requestDTO.concertOptionId(), null, null, null, requestDTO.seatId(), null, null, null);
    }

    public static List<ConcertResponseDTO> toConcertResponseDtoList(List<ConcertFacadeDTO> concertFacadeDTOList) {
        return concertFacadeDTOList.stream()
                .map(concertFacadeDTO -> new ConcertResponseDTO(
                        concertFacadeDTO.concertId(),
                        concertFacadeDTO.concertName(),
                        concertFacadeDTO.concertOptionId(),
                        concertFacadeDTO.concertDate(),
                        concertFacadeDTO.concertReservationDate()
                )).toList();
    }

    public static List<ConcertSeatResponseDTO> toConcertSeatResponseDtoList(List<ConcertFacadeDTO> concertFacadeDTOList) {
        return concertFacadeDTOList.stream()
                .map(concertFacadeDTO -> new ConcertSeatResponseDTO(
                        concertFacadeDTO.seatId(),
                        concertFacadeDTO.seatNum(),
                        concertFacadeDTO.seatPrice(),
                        concertFacadeDTO.seatStatus()
                )).toList();
    }

    public static ConcertSeatResponseDTO toConcertSeatResponseDTO(ConcertFacadeDTO concertFacadeDTO) {
        return new ConcertSeatResponseDTO(concertFacadeDTO.seatId(), concertFacadeDTO.seatNum(), concertFacadeDTO.seatPrice(), concertFacadeDTO.seatStatus());
    }
}
