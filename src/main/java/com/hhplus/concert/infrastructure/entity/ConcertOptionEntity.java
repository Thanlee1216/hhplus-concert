package com.hhplus.concert.infrastructure.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Entity
@Table(name = "concert_option")
@Data
public class ConcertOptionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "concert_option_id")
    private Long concertOptionId;

    @Column(name = "concert_id")
    private Long concertId;

    @Column(name = "concert_option_name")
    private String concertOptionName;

    @Column(name = "concert_date")
    private Long concertDate;

    @Column(name = "concert_reservation_date")
    private Long concertReservationDate;

    @Column(name = "del_yn")
    private String delYn;
}
