package com.hhplus.concert;

import com.hhplus.concert.application.dto.ConcertFacadeDTO;
import com.hhplus.concert.application.facade.ConcertFacade;
import com.hhplus.concert.business.constant.ReservationStatusType;
import com.hhplus.concert.business.domain.ConcertSeatDomain;
import com.hhplus.concert.business.service.ConcertService;
import com.hhplus.concert.business.service.ReservationService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Timestamp;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class PessimisticLockConcertFacadeTest {

    @Autowired
    private ConcertFacade concertFacade;

    @Autowired
    private ConcertService concertService;

    @Autowired
    private ReservationService reservationService;

    @Test
    public void testSeatReservationWithConcurrency() throws InterruptedException {
        // Mock data setup
        Long concertId = 1L;
        Long concertOptionId = 1L;
        Long seatId = 1L;
        Long userId = 1L;

        ConcertFacadeDTO concertFacadeDTO = new ConcertFacadeDTO(
                userId, concertId, "ConcertName", concertOptionId, "OptionName",
                new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()), seatId,
                "A1", 100L, ReservationStatusType.WAIT
        );

        ConcertSeatDomain concertSeatDomain = new ConcertSeatDomain(
                seatId, concertId, concertOptionId, "A1", 100L, ReservationStatusType.WAIT
        );

        // Set up the initial state in the database
//        when(concertService.getSeatInfo(anyLong())).thenReturn(concertSeatDomain);
//        when(concertService.updateSeatStatus(any(ConcertSeatDomain.class))).thenReturn(concertSeatDomain.withSeatStatus(ReservationStatusType.RUN));

        // Measure time
        long startTime = System.currentTimeMillis();

        // Run async tasks
        CompletableFuture<Void> future1 = CompletableFuture.runAsync(() -> concertFacade.seatReservation(concertFacadeDTO));
        CompletableFuture<Void> future2 = CompletableFuture.runAsync(() -> concertFacade.seatReservation(concertFacadeDTO));

        // Wait for all tasks to complete
        CompletableFuture.allOf(future1, future2).join();

        // Measure time
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;

        // Assertions
//        verify(concertService, times(2)).getSeatInfo(anyLong());
//        verify(concertService, times(2)).updateSeatStatus(any(ConcertSeatDomain.class));
//        verify(reservationService, times(2)).createReservation(any());

        System.out.println("Concurrency Test Duration: " + duration + "ms");

        // Verify the final state
        ConcertSeatDomain updatedSeat = concertService.getSeatInfo(seatId);
        assertEquals(ReservationStatusType.RUN, updatedSeat.seatStatus());
    }
}