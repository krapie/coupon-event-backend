package com.krapi.coupon_api.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.krapi.coupon_api.dto.CouponWalletDto;
import com.krapi.coupon_api.global.type.CouponCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;

import java.time.LocalDateTime;

@ApiModel(value = "쿠폰 발급 응답 정보", description = "쿠폰 ID, 쿠폰 코드, 쿠폰 사용 일시를 반환함")
@Builder
public record CouponIssueResponseDto(
        @ApiModelProperty(value = "쿠폰 ID", required = true, example = "8be67b7b-98f4-47dd-be64-839bb77b4534")
        @JsonProperty("id")
        String id,

        @ApiModelProperty(value = "쿠폰 코드", required = true, example = "C0001")
        @JsonProperty("code")
        CouponCode code,

        @ApiModelProperty(value = "쿠폰 사용 일시", required = true, example = "2022-11-23 02:06:59")
        @JsonProperty("issuedAt")
        LocalDateTime issuedAt
) {
    public CouponIssueResponseDto(CouponWalletDto dto) {
        this(dto.id(), dto.code(), dto.issuedAt());
    }
}
