package com.krapi.coupon.domain.model.coupon;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum CouponCode {

    A0001("A0001", "스테이크 할인", 300, 1),
    B0001("B0001", "햄버거 할인", 2000, 1),
    C0001("C0001", "커피 할인", 10000, 1);

    private final String code;
    private final String name;
    private final Integer totalAmount;
    private final Integer maxAmountPerUser;

    public static CouponCode of(String code) {
        if (code == null) {
            throw new IllegalArgumentException("쿠폰 코드 설정이 되어있지 않습니다.");
        }

        return Arrays.stream(CouponCode.values())
                .filter(r -> r.getCode().equals(code))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("쿠폰 코드 설정이 되어있지 않습니다."));
    }
}
