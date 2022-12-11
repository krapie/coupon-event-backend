package com.krapi.coupon_api.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CouponGetRequestDto(
        @JsonProperty("couponId") String couponId
) {
}
