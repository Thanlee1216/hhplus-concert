package com.hhplus.concert.application.facade;

import com.hhplus.concert.application.dto.ConcertFacadeDTO;
import com.hhplus.concert.application.mapper.ConcertFacadeMapper;
import com.hhplus.concert.business.constant.ReservationStatusType;
import com.hhplus.concert.business.domain.ConcertSeatDomain;
import com.hhplus.concert.business.service.ConcertService;
import com.hhplus.concert.business.service.PaymentService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ConcertFacade {

    @Autowired
    private ConcertService concertService;

    @Autowired
    private PaymentService paymentService;

    /**
     * 콘서트 예약 가능 날짜 조회
     * @param concertId
     * @return
     */
    @Transactional
    public List<ConcertFacadeDTO> getDateOfConcertId(Long concertId) {
        return ConcertFacadeMapper.toConcertFacadeDTOListFromOption(concertService.getConcertOptionList(concertId));
    }

    /**
     * 콘서트 예약 가능 좌석 조회
     * @param concertId
     * @param concertOptionId
     * @return
     */
    @Transactional
    public List<ConcertFacadeDTO> getSeatOfConcertIdAndConcertOptionId(Long concertId, Long concertOptionId) {
        return ConcertFacadeMapper.toConcertFacadeDTOListFromSeat(concertService.getSeatList(concertId, concertOptionId));
    }

    /**
     * 콘서트 좌석 예약
     * @param concertFacadeDTO
     * @return
     */
    @Transactional
    public ConcertFacadeDTO seatReservation(ConcertFacadeDTO concertFacadeDTO) {
        ConcertSeatDomain concertSeatDomain = concertService.getSeatInfo(concertFacadeDTO.seatId());
        concertSeatDomain = concertService.updateSeatStatus(concertSeatDomain.withSeatStatus(ReservationStatusType.RUN));
        paymentService.createReservation(concertSeatDomain.convertToReservationDomain().withUserId(concertFacadeDTO.userId()));
        return ConcertFacadeMapper.toConcertFacadeDTO(concertSeatDomain);
    }
}
