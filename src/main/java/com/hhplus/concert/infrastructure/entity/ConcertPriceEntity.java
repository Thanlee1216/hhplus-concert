package com.hhplus.concert.infrastructure.entity;

import com.hhplus.concert.infrastructure.entity.compositekey.ConcertPriceKey;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "concert_price")
@Data
public class ConcertPriceEntity {

    @EmbeddedId
    private ConcertPriceKey concertPriceKey;

    @Column(name = "seat_price")
    private Long seatPrice;

}
