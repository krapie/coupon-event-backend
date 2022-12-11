package com.krapi.coupon_api.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.krapi.coupon_api.dto.CouponWalletDto;
import com.krapi.coupon_api.global.type.CouponCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDateTime;

@ApiModel(
        value = "쿠폰 ID를 통한 쿠폰 정보 조회 응답 정보",
        description = "UserId, 쿠폰 ID, 쿠폰 코드, 쿠폰 발급 일시, 쿠폰 사용 여부, 쿠폰 사용 일시를 반환함"
)
public record CouponGetResponseDto(
        @ApiModelProperty(value = "UserId", required = true, example = "6064830c-6e7e-1534-01c7-5d97cd767f64")
        @JsonProperty("userId")
        String userId,

        @ApiModelProperty(value = "쿠폰 ID", required = true, example = "8be67b7b-98f4-47dd-be64-839bb77b4534")
        @JsonProperty("id")
        String id,

        @ApiModelProperty(value = "쿠폰 코드", required = true, example = "C0001")
        @JsonProperty("code")
        CouponCode code,

        @ApiModelProperty(value = "쿠폰 발급 일시", required = true, example = "2022-11-23 02:06:59")
        @JsonProperty("issuedAt")
        LocalDateTime issuedAt,

        @ApiModelProperty(value = "쿠폰 사용 여부", required = true, example = "true")
        @JsonProperty("isUsed")
        Boolean isUsed,

        @ApiModelProperty(value = "쿠폰 사용 일시", example = "6064830c-6e7e-1534-01c7-5d97cd767f64")
        @JsonProperty("usedAt")
        LocalDateTime usedAt
) {
    public CouponGetResponseDto(CouponWalletDto dto) {
        this(dto.userId(), dto.id(), dto.code(), dto.issuedAt(), dto.isUsed(), dto.usedAt());
    }
}
