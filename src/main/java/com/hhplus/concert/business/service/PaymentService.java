package com.hhplus.concert.business.service;

import com.hhplus.concert.business.constant.ReservationStatusType;
import com.hhplus.concert.business.domain.*;
import com.hhplus.concert.business.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class PaymentService {

    @Autowired
    ConcertRepository concertRepository;

    @Autowired
    ConcertOptionRepository concertOptionRepository;

    @Autowired
    ConcertSeatRepository concertSeatRepository;

    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    PaymentHistoryRepository paymentHistoryRepository;

    public ReservationDomain createReservation(ReservationDomain reservationDomain) {
        return reservationRepository.save(reservationDomain);
    }

    public ReservationDomain updateReservationStatus(ReservationDomain reservationDomain) {
        return reservationRepository.save(reservationDomain.withStatus(ReservationStatusType.DONE));
    }

    public ReservationDomain refreshReservationTime(ReservationDomain reservationDomain) {
        reservationDomain = reservationRepository.findByUserIdAndSeatIdAndStatus(reservationDomain.userId(), reservationDomain.seatId(), ReservationStatusType.RUN);
        reservationDomain = reservationRepository.save(reservationDomain.withReservationTime(new Timestamp(System.currentTimeMillis())));
        return reservationDomain;
    }

    public TicketDomain getTicketInfo(ReservationDomain reservationDomain) {
        ConcertDomain concertDomain = concertRepository.findById(reservationDomain.concertId());
        ConcertOptionDomain concertOptionDomain = concertOptionRepository.findByConcertIdAndConcertOptionId(reservationDomain.concertId(), reservationDomain.concertOptionId());
        ConcertSeatDomain concertSeatDomain = concertSeatRepository.findById(reservationDomain.seatId());
        return TicketDomain.of(concertDomain, concertOptionDomain, concertSeatDomain);
    }

    public TicketDomain insertTicketPayment(TicketDomain ticketDomain, PaymentHistoryDomain paymentHistoryDomain) {
        paymentHistoryRepository.insertHistory(paymentHistoryDomain);
        return ticketRepository.insertTicket(ticketDomain);
    }
}
