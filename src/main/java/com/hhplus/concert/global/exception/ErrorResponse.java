package com.hhplus.concert.global.exception;

public record ErrorResponse(
        String code,
        String message
) {
}
