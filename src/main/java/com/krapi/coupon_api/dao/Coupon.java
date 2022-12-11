package com.krapi.coupon_api.dao;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Getter
@Table("coupon")
public class Coupon {

    @Id
    @Column("coupon_code")
    private String code;

    private Integer discountValue;
    private Integer maxAmountPerUser;
    private Integer totalAmount;
    private Integer leftAmount;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    public void updateLeftAmount(Long count) {
        if (count == null) return;

        if (count >= totalAmount) {
            this.leftAmount = 0;
        }
        else {
            this.leftAmount = this.totalAmount - count.intValue();
        }
    }
}
