package com.krapi.coupon_api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.krapi.coupon_api.dao.CouponWallet;
import com.krapi.coupon_api.global.type.CouponCode;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record CouponWalletDto(
        @JsonProperty("userId") String userId,
        @JsonProperty("id") String id,
        @JsonProperty("code") CouponCode code,
        @JsonProperty("issuedAt") LocalDateTime issuedAt,
        @JsonProperty("isUsed") Boolean isUsed,
        @JsonProperty("usedAt") LocalDateTime usedAt
) {
    public CouponWalletDto(CouponWallet couponWallet) {
        this(
                couponWallet.getUserId(),
                couponWallet.getUuid(),
                CouponCode.of(couponWallet.getCouponCode()),
                couponWallet.getIssuedAt(),
                couponWallet.getIsUsed(),
                couponWallet.getUsedAt()
        );
    }
}
