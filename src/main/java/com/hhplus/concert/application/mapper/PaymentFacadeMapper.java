package com.hhplus.concert.application.mapper;

import com.hhplus.concert.application.dto.PaymentFacadeDTO;
import com.hhplus.concert.business.domain.ReservationDomain;

public class PaymentFacadeMapper {

    public static ReservationDomain toReservationDomain(PaymentFacadeDTO facadeDTO) {
        return new ReservationDomain(null, facadeDTO.userId(), null, null, facadeDTO.seatId(), null, null);
    }

    public static PaymentFacadeDTO toPaymentFacadeDTO(ReservationDomain reservationDomain) {
        return new PaymentFacadeDTO(reservationDomain.userId(), reservationDomain.seatId(), reservationDomain.reservationId());
    }
}
