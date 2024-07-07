package com.hhplus.concert.infrastructure.entity;

import com.hhplus.concert.infrastructure.entity.compositekey.ConcertOptionKey;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Entity
@Table(name = "concert_option")
@Data
public class ConcertOptionEntity {

    @EmbeddedId
    private ConcertOptionKey concertOptionKey;

    @MapsId("concertOptionId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "concert_option_id")
    private Long concertOptionId;

    @Column(name = "concert_option_name")
    private String concertOptionName;

    @Column(name = "hall_id")
    private Long hallId;

    @Column(name = "concert_date")
    private Timestamp concertDate;

    @Column(name = "concert_reservation_date")
    private Timestamp concertReservationDate;

    @Column(name = "del_yn")
    private String delYn;
}
