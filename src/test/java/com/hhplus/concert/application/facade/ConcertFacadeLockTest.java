package com.hhplus.concert.application.facade;

import com.hhplus.concert.application.dto.ConcertFacadeDTO;
import com.hhplus.concert.business.constant.ReservationStatusType;
import com.hhplus.concert.business.domain.ConcertSeatDomain;
import com.hhplus.concert.infrastructure.persistence.dataaccess.ConcertSeatJpaRepository;
import com.hhplus.concert.infrastructure.persistence.mapper.ConcertSeatEntityMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicReference;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class ConcertFacadeLockTest {

    @Autowired
    private ConcertFacade concertFacade;

    @Autowired
    private ConcertSeatJpaRepository concertSeatJpaRepository;

    @DisplayName("좌석 예약 동시성 테스트")
    @Test
    public void testConcurrentSeatReservation() {
        // Given
        Long seatId = 1L;
        Long concertId = 1L;
        Long concertOptionId = 1L;
        Long userId1 = 100L;
        Long userId2 = 200L;

        ConcertFacadeDTO reservationRequest1 = new ConcertFacadeDTO(userId1, concertId, "싸이의 흠뻑쇼", concertOptionId, "시즌 1", new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()), seatId, "A1", 50000L, ReservationStatusType.WAIT);
        ConcertFacadeDTO reservationRequest2 = new ConcertFacadeDTO(userId2, concertId, "싸이의 흠뻑쇼", concertOptionId, "시즌 1", new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()), seatId, "A1", 50000L, ReservationStatusType.WAIT);

        //이선좌 확인을 위한 객체
        AtomicReference<Exception> exceptionRef1 = new AtomicReference<>();
        AtomicReference<Exception> exceptionRef2 = new AtomicReference<>();
        //성공 요청 확인을 위한 객체
        AtomicReference<ConcertFacadeDTO> successRef1 = new AtomicReference<>();
        AtomicReference<ConcertFacadeDTO> successRef2 = new AtomicReference<>();

        // When
        CompletableFuture<Void> reservationFuture1 = CompletableFuture.runAsync(() -> {
            try {
                successRef1.set(concertFacade.seatReservation(reservationRequest1));
            } catch (Exception e) {
                exceptionRef1.set(e);
            }
        });

        CompletableFuture<Void> reservationFuture2 = CompletableFuture.runAsync(() -> {
            try {
                successRef2.set(concertFacade.seatReservation(reservationRequest2));
            } catch (Exception e) {
                exceptionRef2.set(e);
            }
        });

        CompletableFuture.allOf(reservationFuture1, reservationFuture2).join();

        // Then
        ConcertSeatDomain concertSeatDomain = ConcertSeatEntityMapper.toConcertSeatDomain(concertSeatJpaRepository.findById(seatId));

        // 예약된 좌석의 상태가 RUN인지 확인
        assertThat(concertSeatDomain.seatStatus()).isEqualTo(ReservationStatusType.RUN);

        // 하나의 요청은 성공적으로 처리되고, 다른 하나는 예외를 던졌는지 확인
        if (exceptionRef1.get() == null) {
            assertThat(successRef1.get()).isNotNull();
            assertThat(exceptionRef2.get()).isNotNull().hasMessage("이미 선택된 좌석입니다.");
        } else {
            assertThat(exceptionRef1.get()).isNotNull().hasMessage("이미 선택된 좌석입니다.");
            assertThat(successRef2.get()).isNotNull();
        }
    }
}
