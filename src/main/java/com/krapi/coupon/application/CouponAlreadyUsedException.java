package com.krapi.coupon.application;

public class CouponAlreadyUsedException extends RuntimeException {

    public CouponAlreadyUsedException() {
        super(ErrorCode.COUPON_ALREADY_USED.getCode());
    }
}
