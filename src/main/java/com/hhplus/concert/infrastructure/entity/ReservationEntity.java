package com.hhplus.concert.infrastructure.entity;

import com.hhplus.concert.business.constant.ReservationStatusType;
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

    @Column(name = "user_id")
    private Long userId;

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

    public ReservationEntity() {}
    public ReservationEntity(Long reservationId, Long userId, Long concertId, Long concertOptionId, Long seatId, Timestamp reservationTime, ReservationStatusType status) {
        this.reservationId = reservationId;
        this.userId = userId;
        this.concertId = concertId;
        this.concertOptionId = concertOptionId;
        this.seatId = seatId;
        this.reservationTime = reservationTime;
        this.status = status;
    }

}
