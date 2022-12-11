package com.krapi.coupon_api.dao;

import com.krapi.coupon_api.exception.CouponAlreadyUsedException;
import com.krapi.coupon_api.global.utils.UuidGenerator;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Getter
@Table("coupon_wallet")
public class CouponWallet {

    @Id
    @Column("coupon_wallet_id")
    private Long id;

    @Column("coupon_wallet_uuid")
    private String uuid;

    private String couponCode;

    @Column("user_id")
    private String userId;

    private Boolean isUsed;

    private LocalDateTime issuedAt;
    private LocalDateTime usedAt;
    private LocalDateTime expiredAt;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    public CouponWallet(String userId, String couponCode) {
        this.uuid = UuidGenerator.generate().toString();
        this.couponCode = couponCode;
        this.userId = userId;
        this.isUsed = false;
        this.issuedAt = LocalDateTime.now();
        this.expiredAt = null;
    }

    public void useCoupon() {
        if (isUsed) {
            throw new CouponAlreadyUsedException();
        }

        isUsed = true;
        usedAt = LocalDateTime.now();
    }
}
