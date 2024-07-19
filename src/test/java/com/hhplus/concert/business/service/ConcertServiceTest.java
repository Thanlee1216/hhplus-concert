package com.hhplus.concert.business.service;

import com.hhplus.concert.business.constant.ReservationStatusType;
import com.hhplus.concert.business.domain.ConcertOptionDomain;
import com.hhplus.concert.business.domain.ConcertSeatDomain;
import com.hhplus.concert.business.repository.ConcertOptionRepository;
import com.hhplus.concert.business.repository.ConcertSeatRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ConcertServiceTest {

    @InjectMocks
    ConcertService concertService;

    @Mock
    ConcertOptionRepository concertOptionRepository;

    @Mock
    ConcertSeatRepository concertSeatRepository;

    @DisplayName("공연 옵션 목록을 조회하는 테스트")
    @Test
    void getConcertOptionList() {
        //given
        long concertId = 1L;
        List<ConcertOptionDomain> concertOptions = List.of(
                new ConcertOptionDomain(concertId, 1L, "Option 1", new Timestamp(System.currentTimeMillis() - 1000L), new Timestamp(System.currentTimeMillis() - 1000L)),
                new ConcertOptionDomain(concertId, 2L, "Option 2", new Timestamp(System.currentTimeMillis() - 2000L), new Timestamp(System.currentTimeMillis() - 2000L))
        );
        when(concertOptionRepository.findByConcertIdAndConcertReservationDateBefore(anyLong(), any(Timestamp.class)))
                .thenReturn(concertOptions);

        //when
        List<ConcertOptionDomain> result = concertService.getConcertOptionList(concertId);

        //then
        assertThat(result).isEqualTo(concertOptions);
    }

    @DisplayName("공연 좌석 목록을 조회하는 테스트")
    @Test
    void getSeatList() {
        //given
        long concertId = 1L;
        long concertOptionId = 1L;
        List<ConcertSeatDomain> concertSeats = List.of(
                new ConcertSeatDomain(1L, concertId, concertOptionId, "A1", 5000L, ReservationStatusType.WAIT),
                new ConcertSeatDomain(2L, concertId, concertOptionId, "A2", 5000L, ReservationStatusType.WAIT)
        );
        when(concertSeatRepository.findByConcertIdAndConcertOptionId(concertId, concertOptionId))
                .thenReturn(concertSeats);

        //when
        List<ConcertSeatDomain> result = concertService.getSeatList(concertId, concertOptionId);

        //then
        assertThat(result).isEqualTo(concertSeats);
    }

    @DisplayName("공연 좌석 상태를 업데이트하는 테스트")
    @Test
    void updateSeatStatus() {
        //given
        ConcertSeatDomain concertSeatDomain = new ConcertSeatDomain(1L, 1L, 1L, "A1", 5000L, ReservationStatusType.RUN);
        when(concertSeatRepository.updateStatus(any(ConcertSeatDomain.class)))
                .thenReturn(concertSeatDomain);

        //when
        ConcertSeatDomain result = concertService.updateSeatStatus(concertSeatDomain);

        //then
        assertThat(result).isEqualTo(concertSeatDomain);
    }

    @DisplayName("공연 좌석 정보를 조회하는 테스트")
    @Test
    void getSeatInfo() {
        //given
        long seatId = 1L;
        ConcertSeatDomain concertSeatDomain = new ConcertSeatDomain(seatId, 1L, 1L, "A1", 5000L, ReservationStatusType.WAIT);
        when(concertSeatRepository.findById(seatId)).thenReturn(concertSeatDomain);

        //when
        ConcertSeatDomain result = concertService.getSeatInfo(seatId);

        //then
        assertThat(result).isEqualTo(concertSeatDomain);
    }
}