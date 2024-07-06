package com.hhplus.concert.presentation.dto.response;

import java.sql.Timestamp;

public record BalanceHistoryResponseDTO(
        long balance_history_id,
        long amount,
        String transaction_type,
        Timestamp transaction_date
) {
}
