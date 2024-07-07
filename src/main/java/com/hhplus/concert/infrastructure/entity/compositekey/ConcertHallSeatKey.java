package com.hhplus.concert.infrastructure.entity.compositekey;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Embeddable
@Data
public class ConcertHallSeatKey implements Serializable {

    @Column(name = "hall_id")
    private Long hallId;

    @Column(name = "seat_id")
    private Long seatId;
}
