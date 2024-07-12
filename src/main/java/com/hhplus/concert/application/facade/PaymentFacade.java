package com.hhplus.concert.application.facade;

import com.hhplus.concert.application.dto.PaymentFacadeDTO;
import com.hhplus.concert.application.mapper.PaymentFacadeMapper;
import com.hhplus.concert.business.constant.ReservationStatusType;
import com.hhplus.concert.business.constant.TicketStatusType;
import com.hhplus.concert.business.domain.PaymentHistoryDomain;
import com.hhplus.concert.business.domain.ReservationDomain;
import com.hhplus.concert.business.domain.TicketDomain;
import com.hhplus.concert.business.service.ConcertService;
import com.hhplus.concert.business.service.PaymentService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaymentFacade {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private ConcertService concertService;

    /**
     * 결제
     * 결제하다가 예약한 자리 만료되면 안되니 만료 시간 갱신
     * 티켓을 결제하고 좌석의 상태를 변경
     * @param facadeDTO
     * @return
     */
    @Transactional
    public PaymentFacadeDTO payment(PaymentFacadeDTO facadeDTO) {
        ReservationDomain reservationDomain = paymentService.refreshReservationTime(PaymentFacadeMapper.toReservationDomain(facadeDTO));
        TicketDomain ticketDomain = paymentService.getTicketInfo(reservationDomain);
        PaymentHistoryDomain paymentHistoryDomain = PaymentHistoryDomain.of(ticketDomain);
        paymentService.insertTicketPayment(ticketDomain.withIdsAndStatus(reservationDomain.userId(), reservationDomain.reservationId(), TicketStatusType.CANCELED), paymentHistoryDomain);
        paymentService.updateReservationStatus(reservationDomain);
        concertService.updateSeatStatus(ticketDomain.concertSeatDomain().withSeatStatus(ReservationStatusType.RUN));
        return PaymentFacadeMapper.toPaymentFacadeDTO(reservationDomain);
    }
}
