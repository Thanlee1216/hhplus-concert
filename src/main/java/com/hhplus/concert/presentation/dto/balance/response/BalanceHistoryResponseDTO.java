package com.hhplus.concert.presentation.dto.balance.response;

import java.sql.Timestamp;

public record BalanceHistoryResponseDTO(
        Long balance_history_id,
        Long amount,
        String transaction_type,
        Timestamp transaction_date
) {
}
