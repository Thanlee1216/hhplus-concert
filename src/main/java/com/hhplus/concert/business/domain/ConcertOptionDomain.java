package com.hhplus.concert.business.domain;

import java.io.Serializable;
import java.sql.Timestamp;

public record ConcertOptionDomain(
        Long concertid,
        Long concertOptionId,
        String concertOptionName,
        Timestamp concertDate,
        Timestamp concertReservationDate
) implements Serializable {
}
