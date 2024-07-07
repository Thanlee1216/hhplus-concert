package com.hhplus.concert.infrastructure.entity.compositekey;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Embeddable
@Data
public class ConcertPriceKey implements Serializable {

    @Column(name = "concert_id")
    private Long concertId;

    @Column(name = "concert_option_id")
    private Long concertOptionId;

    @Column(name = "seat_id")
    private Long seatId;
}
