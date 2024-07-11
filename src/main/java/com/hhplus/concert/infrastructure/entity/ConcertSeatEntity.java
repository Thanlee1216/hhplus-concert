package com.hhplus.concert.infrastructure.entity;

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
    private String seatStatus;

}
