package com.hhplus.concert.business.service;

import com.hhplus.concert.business.constant.ReservationStatusType;
import com.hhplus.concert.business.constant.TicketStatusType;
import com.hhplus.concert.business.domain.*;
import com.hhplus.concert.business.repository.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PaymentServiceTest {

    @InjectMocks
    PaymentService paymentService;

    @Mock
    ConcertRepository concertRepository;

    @Mock
    ConcertOptionRepository concertOptionRepository;

    @Mock
    ConcertSeatRepository concertSeatRepository;

    @Mock
    TicketRepository ticketRepository;

    @Mock
    PaymentHistoryRepository paymentHistoryRepository;

    @DisplayName("티켓 정보를 조회하는 테스트")
    @Test
    void getTicketInfo() {
        //given
        ReservationDomain reservationDomain = new ReservationDomain(1L, 1L, 1L, 1L, 1L, new Timestamp(System.currentTimeMillis()), ReservationStatusType.RUN);
        ConcertDomain concertDomain = new ConcertDomain(1L, "Concert");
        ConcertOptionDomain concertOptionDomain = new ConcertOptionDomain(1L, 1L, "Option 1", new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()));
        ConcertSeatDomain concertSeatDomain = new ConcertSeatDomain(1L, 1L, 1L, "A1", 5000L, ReservationStatusType.WAIT);
        TicketDomain ticketDomain = TicketDomain.of(concertDomain, concertOptionDomain, concertSeatDomain);

        when(concertRepository.findById(any(Long.class))).thenReturn(concertDomain);
        when(concertOptionRepository.findByConcertIdAndConcertOptionId(any(Long.class), any(Long.class))).thenReturn(concertOptionDomain);
        when(concertSeatRepository.findById(any(Long.class))).thenReturn(concertSeatDomain);

        //when
        TicketDomain result = paymentService.getTicketInfo(reservationDomain);

        //then
        assertThat(result).isEqualTo(ticketDomain);
    }

    @DisplayName("티켓 결제를 처리하는 테스트")
    @Test
    void insertTicketPayment() {
        //given
        TicketDomain ticketDomain = new TicketDomain(1L, 1L, 1L, 1L, "Concert", 1L, "Option 1", new Timestamp(System.currentTimeMillis()), 1L, "A1", 5000L, TicketStatusType.COMPLETED);
        PaymentHistoryDomain paymentHistoryDomain = new PaymentHistoryDomain(1L, 1L, 1L, 5000L, null, new Timestamp(System.currentTimeMillis()));
        when(paymentHistoryRepository.insertHistory(any(PaymentHistoryDomain.class))).thenReturn(paymentHistoryDomain);
        when(ticketRepository.insertTicket(any(TicketDomain.class))).thenReturn(ticketDomain);

        //when
        TicketDomain result = paymentService.insertTicketPayment(ticketDomain, paymentHistoryDomain);

        //then
        assertThat(result).isEqualTo(ticketDomain);
    }
}