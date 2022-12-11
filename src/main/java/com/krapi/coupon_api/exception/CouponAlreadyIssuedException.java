package com.krapi.coupon_api.exception;

import com.krapi.coupon_api.global.type.ErrorCode;

public class CouponAlreadyIssuedException extends RuntimeException {

    public CouponAlreadyIssuedException() {
        super(ErrorCode.COUPON_ALREADY_ISSUED.getCode());
    }
}
