package com.hhplus.concert.presentation.dto.response;

import java.sql.Timestamp;

public record PaymentHistoryResponseDTO(
        long payment_history_id,
        long amount,
        String transaction_type,
        Timestamp transaction_date,
        long user_id,
        long concert_id,
        String concert_name,
        long concert_option_id,
        Timestamp concert_date,
        long concert_seat_id,
        String concert_seat_num,
        long concert_seat_price
) {
}
