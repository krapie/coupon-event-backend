package com.krapi.coupon.domain.model.wallet;

import com.krapi.coupon.application.CouponAlreadyUsedException;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.UUID;

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

    public CouponWallet(String userId, String couponCode, UUID uuid) {
        this.uuid = uuid.toString();
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
