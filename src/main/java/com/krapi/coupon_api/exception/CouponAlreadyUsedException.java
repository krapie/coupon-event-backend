package com.krapi.coupon_api.exception;

import com.krapi.coupon_api.global.type.ErrorCode;

public class CouponAlreadyUsedException extends RuntimeException {

    public CouponAlreadyUsedException() {
        super(ErrorCode.COUPON_ALREADY_USED.getCode());
    }
}
