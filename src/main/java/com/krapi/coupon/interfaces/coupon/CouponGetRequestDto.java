package com.krapi.coupon.interfaces.coupon;

import com.fasterxml.jackson.annotation.JsonProperty;

record CouponGetRequestDto(
        @JsonProperty("couponId") String couponId
) {
}
