package com.krapi.coupon.application;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.krapi.coupon.domain.model.coupon.Coupon;
import com.krapi.coupon.domain.model.coupon.CouponCode;

public record CouponStatusDto(
    @JsonProperty("code") CouponCode code,
    @JsonProperty("name") String name,
    @JsonProperty("discountValue") Integer discountValue,
    @JsonProperty("totalAmount") Integer totalAmount,
    @JsonProperty("leftAmount") Integer leftAmount
) {
    public CouponStatusDto(Coupon coupon) {
        this(
                CouponCode.of(coupon.getCode()),
                CouponCode.of(coupon.getCode()).getName(),
                coupon.getDiscountValue(),
                coupon.getTotalAmount(),
                coupon.getLeftAmount()
        );
    }
}
