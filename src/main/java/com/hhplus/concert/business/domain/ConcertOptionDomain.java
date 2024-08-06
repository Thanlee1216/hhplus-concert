package com.hhplus.concert.business.domain;

import java.sql.Timestamp;

public record ConcertOptionDomain(
        Long concertid,
        Long concertOptionId,
        String concertOptionName,
        Long concertDate,
        Long concertReservationDate
) {
}
