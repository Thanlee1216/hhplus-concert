package com.hhplus.concert.application.facade;

import com.hhplus.concert.application.dto.ConcertFacadeDTO;
import com.hhplus.concert.application.mapper.ConcertFacadeMapper;
import com.hhplus.concert.business.constant.ReservationStatusType;
import com.hhplus.concert.business.domain.ConcertSeatDomain;
import com.hhplus.concert.business.service.ConcertService;
import com.hhplus.concert.business.service.ReservationService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.logging.Logger;

@Component
public class ConcertFacade {

    private final Logger logger = Logger.getLogger(ConcertFacade.class.getName());

    @Autowired
    private ConcertService concertService;

    @Autowired
    private ReservationService reservationService;

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
        ConcertSeatDomain concertSeatDomain = null;
        try {
            concertSeatDomain = concertService.getSeatInfo(concertFacadeDTO.seatId()); //LockModeType.PESSIMISTIC_READ를 걸었는데
            logger.info("Select는 완료");//왜 얘도 실행 안하고 기다릴까요?
            Thread.sleep(10000);
        }catch (Exception e) {
            e.printStackTrace();
        }
        concertSeatDomain = concertService.updateSeatStatus(concertSeatDomain.withSeatStatus(ReservationStatusType.RUN));
        reservationService.createReservation(concertSeatDomain.convertToReservationDomain().withUserId(concertFacadeDTO.userId()));
        return ConcertFacadeMapper.toConcertFacadeDTO(concertSeatDomain);
    }
}
