package com.hhplus.concert.infrastructure.entity;

import com.hhplus.concert.infrastructure.entity.compositekey.ConcertHallSeatKey;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "concert_hall_seat")
@Data
public class ConcertHallSeatEntity {

    @EmbeddedId
    private ConcertHallSeatKey concertHallSeatKey;

    @MapsId("concertOptionId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seat_id")
    private Long seatId;

    @Column(name = "seat_name")
    private String seatName;

    @Column(name = "del_yn")
    private String delYn;
}
