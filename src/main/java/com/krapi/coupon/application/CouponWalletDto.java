package com.krapi.coupon.application;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.krapi.coupon.domain.model.wallet.CouponWallet;
import com.krapi.coupon.domain.model.coupon.CouponCode;
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
