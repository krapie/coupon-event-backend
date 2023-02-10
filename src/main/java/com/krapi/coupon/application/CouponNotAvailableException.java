package com.krapi.coupon.application;

class CouponNotAvailableException extends RuntimeException {

    public CouponNotAvailableException() {
        super(ErrorCode.COUPON_NOT_AVAILABLE.getCode());
    }
}
