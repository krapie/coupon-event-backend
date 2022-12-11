package com.krapi.coupon_api.global.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.krapi.coupon_api.global.type.ErrorCode;

public record ErrorResponseDto(
        @JsonProperty("code") String code,
        @JsonProperty("message") String message
) {
    public ErrorResponseDto(ErrorCode errorCode) {
        this(errorCode.getCode(), errorCode.getMessage());
    }
}
