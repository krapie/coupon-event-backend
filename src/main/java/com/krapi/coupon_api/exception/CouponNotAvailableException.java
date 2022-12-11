package com.krapi.coupon_api.exception;

import com.krapi.coupon_api.global.type.ErrorCode;

public class CouponNotAvailableException extends RuntimeException {

    public CouponNotAvailableException() {
        super(ErrorCode.COUPON_NOT_AVAILABLE.getCode());
    }
}
