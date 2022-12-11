package com.krapi.coupon_api.service;

import com.krapi.coupon_api.dto.CouponWalletDto;
import com.krapi.coupon_api.dto.response.CouponStatusGetAllResponseDto;
import org.springframework.data.domain.PageRequest;
import reactor.core.publisher.Mono;


public interface CouponService {

    Mono<CouponWalletDto> issueCoupon(String userId, String code);
    Mono<CouponWalletDto> getCoupon(String id);
    Mono<CouponStatusGetAllResponseDto> getAllCouponStatus(PageRequest pageRequest);
}
