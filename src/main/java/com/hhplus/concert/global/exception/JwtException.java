package com.hhplus.concert.global.exception;

public class JwtException extends RuntimeException {
    private String code;
    private String message;

    public JwtException(String code) {
        this.code = code;
    }
    public JwtException(String code, String message) {
        this.code = code;
        this.message = message;
    }
    public String getCode() {
        return this.code;
    }
    public String getMessage() {return this.message;}
}
