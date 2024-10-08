package com.hhplus.concert.infrastructure.entity;

import com.hhplus.concert.business.constant.ReservationStatusType;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "concert_seat")
@Data
public class ConcertSeatEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seat_id")
    private Long seatId;

    @Column(name = "concert_id")
    private Long concertId;

    @Column(name = "concert_option_id")
    private Long concertOptionId;

    @Column(name = "seat_num")
    private String seatNum;

    @Column(name = "seat_price")
    private Long seatPrice;

    @Column(name = "seat_status")
    @Enumerated(EnumType.STRING)
    private ReservationStatusType seatStatus;

    public ConcertSeatEntity() {}
    public ConcertSeatEntity(Long seatId, Long concertId, Long concertOptionId, String seatNum, Long seatPrice, ReservationStatusType seatStatus) {
        this.seatId = seatId;
        this.concertId = concertId;
        this.concertOptionId = concertOptionId;
        this.seatNum = seatNum;
        this.seatPrice = seatPrice;
        this.seatStatus = seatStatus;
    }
}
