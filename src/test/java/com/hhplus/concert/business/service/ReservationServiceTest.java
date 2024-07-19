package com.hhplus.concert.business.service;

import com.hhplus.concert.business.constant.ReservationStatusType;
import com.hhplus.concert.business.domain.ReservationDomain;
import com.hhplus.concert.business.repository.ReservationRepository;
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
class ReservationServiceTest {

    @InjectMocks
    ReservationService reservationService;

    @Mock
    ReservationRepository reservationRepository;

    @DisplayName("예약을 생성하는 테스트")
    @Test
    void createReservation() {
        //given
        ReservationDomain reservationDomain = new ReservationDomain(1L, 1L, 1L, 1L, 1L, new Timestamp(System.currentTimeMillis()), ReservationStatusType.RUN);
        when(reservationRepository.save(any(ReservationDomain.class))).thenReturn(reservationDomain);

        //when
        ReservationDomain result = reservationService.createReservation(reservationDomain);

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
        ReservationDomain result = reservationService.updateReservationStatus(reservationDomain);

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
        ReservationDomain result = reservationService.refreshReservationTime(reservationDomain);

        //then
        assertThat(result).isEqualTo(refreshedReservation);
    }

}