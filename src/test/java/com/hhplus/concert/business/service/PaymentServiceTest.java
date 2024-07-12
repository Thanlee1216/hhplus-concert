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
    ReservationRepository reservationRepository;

    @Mock
    TicketRepository ticketRepository;

    @Mock
    PaymentHistoryRepository paymentHistoryRepository;

    @DisplayName("예약을 생성하는 테스트")
    @Test
    void createReservation() {
        //given
        ReservationDomain reservationDomain = new ReservationDomain(1L, 1L, 1L, 1L, 1L, new Timestamp(System.currentTimeMillis()), ReservationStatusType.RUN);
        when(reservationRepository.save(any(ReservationDomain.class))).thenReturn(reservationDomain);

        //when
        ReservationDomain result = paymentService.createReservation(reservationDomain);

        //then
        assertThat(result).isEqualTo(reservationDomain);
    }

    @DisplayName("예약 상태를 업데이트하는 테스트")
    @Test
    void updateReservationStatus() {
        //given
        ReservationDomain reservationDomain = new ReservationDomain(1L, 1L, 1L, 1L, 1L, new Timestamp(System.currentTimeMillis()), ReservationStatusType.RUN);
        ReservationDomain updatedReservation = reservationDomain.withStatus(ReservationStatusType.DONE);
        when(reservationRepository.save(any(ReservationDomain.class))).thenReturn(updatedReservation);

        //when
        ReservationDomain result = paymentService.updateReservationStatus(reservationDomain);

        //then
        assertThat(result).isEqualTo(updatedReservation);
    }

    @DisplayName("예약 시간을 갱신하는 테스트")
    @Test
    void refreshReservationTime() {
        //given
        ReservationDomain reservationDomain = new ReservationDomain(1L, 1L, 1L, 1L, 1L, new Timestamp(System.currentTimeMillis()), ReservationStatusType.RUN);
        ReservationDomain refreshedReservation = reservationDomain.withReservationTime(new Timestamp(System.currentTimeMillis()));
        when(reservationRepository.findByUserIdAndSeatIdAndStatus(any(Long.class), any(Long.class), any(ReservationStatusType.class)))
                .thenReturn(reservationDomain);
        when(reservationRepository.save(any(ReservationDomain.class))).thenReturn(refreshedReservation);

        //when
        ReservationDomain result = paymentService.refreshReservationTime(reservationDomain);

        //then
        assertThat(result).isEqualTo(refreshedReservation);
    }

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