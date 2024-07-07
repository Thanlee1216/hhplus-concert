package com.hhplus.concert.infrastructure.entity;

import com.hhplus.concert.global.constant.ReservationStatusType;
import com.hhplus.concert.infrastructure.entity.compositekey.ConcertSeatReservationKey;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "concert_price")
@Data
public class ConcertSeatReservationEntity {

    @EmbeddedId
    private ConcertSeatReservationKey concertSeatReservationKey;

    @MapsId("seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seq")
    private Long seq;

    @Column(name = "token")
    private String token;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ReservationStatusType status;

}
