package com.hhplus.concert.business.service;

import com.hhplus.concert.business.constant.ReservationStatusType;
import com.hhplus.concert.business.domain.ReservationDomain;
import com.hhplus.concert.business.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class ReservationService {

    @Autowired
    ReservationRepository reservationRepository;

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
}
