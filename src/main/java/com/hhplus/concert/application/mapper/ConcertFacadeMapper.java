package com.hhplus.concert.application.mapper;

import com.hhplus.concert.application.dto.ConcertFacadeDTO;
import com.hhplus.concert.business.domain.ConcertOptionDomain;
import com.hhplus.concert.business.domain.ConcertSeatDomain;

import java.util.List;

public class ConcertFacadeMapper {

    public static List<ConcertFacadeDTO> toConcertFacadeDTOListFromOption(List<ConcertOptionDomain> concertOptionDomainList) {
        return concertOptionDomainList.stream()
                .map(concertOptionDomain -> new ConcertFacadeDTO(
                        null,
                        concertOptionDomain.concertid(),
                        null,
                        concertOptionDomain.concertOptionId(),
                        concertOptionDomain.concertOptionName(),
                        concertOptionDomain.concertDate(),
                        concertOptionDomain.concertReservationDate(),
                        null,
                        null,
                        null,
                        null
                )).toList();
    }

    public static List<ConcertFacadeDTO> toConcertFacadeDTOListFromSeat(List<ConcertSeatDomain> concertSeatDomainList) {
        return concertSeatDomainList.stream()
                .map(concertSeatDomain -> new ConcertFacadeDTO(
                        null,
                        concertSeatDomain.concertId(),
                        null,
                        concertSeatDomain.concertOptionId(),
                        null,
                        null,
                        null,
                        concertSeatDomain.seatId(),
                        concertSeatDomain.seatNum(),
                        concertSeatDomain.seatPrice(),
                        concertSeatDomain.seatStatus()
                )).toList();
    }

    public static ConcertSeatDomain toConcertSeatDomain(ConcertFacadeDTO concertFacadeDTO) {
        return new ConcertSeatDomain(concertFacadeDTO.seatId(), concertFacadeDTO.concertId(), concertFacadeDTO.concertOptionId(), null, null, null, null);
    }

    public static ConcertFacadeDTO toConcertFacadeDTO(ConcertSeatDomain concertSeatDomain) {
        return new ConcertFacadeDTO(null, concertSeatDomain.concertId(), null, concertSeatDomain.concertOptionId(), null, null, null, concertSeatDomain.seatId(), concertSeatDomain.seatNum(), concertSeatDomain.seatPrice(), concertSeatDomain.seatStatus());
    }
}
