package com.krapi.coupon.application;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    UNEXPECTED("ERR000", "예상치 못한 예외가 발생했습니다."),

    COUPON_ALREADY_ISSUED("ERR001", "이미 쿠폰을 모두 받으셨습니다."),
    COUPON_ALREADY_USED("ERR002", "해당 쿠폰은 이미 사용되었습니다."),
    COUPON_NOT_AVAILABLE("ERR003", "쿠폰이 모두 소진되었습니다.");

    private final String code;
    private final String message;

    public static ErrorCode of(String code) {
        if (code == null) {
            throw new IllegalArgumentException("에러 코드 설정이 되어있지 않습니다.");
        }

        return Arrays.stream(ErrorCode.values())
                .filter(r -> r.getCode().equals(code))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("에러 코드 설정이 되어있지 않습니다."));
    }
}
