package com.hhplus.concert.infrastructure.entity;

import com.hhplus.concert.global.constant.ReservationStatusType;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Entity
@Table(name = "reservation")
@Data
public class ReservationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id")
    private Long reservationId;

    @Column(name = "concert_id")
    private Long concertId;

    @Column(name = "concert_option_id")
    private Long concertOptionId;

    @Column(name = "seat_id")
    private Long seatId;

    @Column(name = "reservation_time")
    private Timestamp reservationTime;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ReservationStatusType status;

}
