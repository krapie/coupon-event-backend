package com.krapi.coupon.interfaces.coupon;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "쿠폰 발급 요청 정보", description = "사용자 ID, 쿠폰 코드가 필요함")
record CouponIssueRequestDto (
        @ApiModelProperty(value = "사용자 ID", required = true, example = "6064830c-6e7e-1534-01c7-5d97cd767f64")
        @JsonProperty("userId")
        String userId,

        @ApiModelProperty(value = "쿠폰 코드", required = true, example = "C0001")
        @JsonProperty("couponCode")
        String couponCode
) {
}
