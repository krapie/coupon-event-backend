package com.krapi.coupon.interfaces.coupon;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.krapi.coupon.application.ErrorCode;

public record ErrorResponseDto(
        @JsonProperty("code") String code,
        @JsonProperty("message") String message
) {
    public ErrorResponseDto(ErrorCode errorCode) {
        this(errorCode.getCode(), errorCode.getMessage());
    }
}
