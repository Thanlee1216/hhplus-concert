package com.hhplus.concert.presentation.dto.payment.response;

import java.sql.Timestamp;

public record PaymentHistoryResponseDTO(
        Long payment_history_id,
        Long amount,
        String transaction_type,
        Timestamp transaction_date,
        Long user_id,
        Long concert_id,
        String concert_name,
        Long concert_option_id,
        Timestamp concert_date,
        Long concert_seat_id,
        String concert_seat_num,
        Long concert_seat_price
) {
}
