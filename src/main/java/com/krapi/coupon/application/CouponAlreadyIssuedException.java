package com.krapi.coupon.application;

class CouponAlreadyIssuedException extends RuntimeException {

    public CouponAlreadyIssuedException() {
        super(ErrorCode.COUPON_ALREADY_ISSUED.getCode());
    }
}
