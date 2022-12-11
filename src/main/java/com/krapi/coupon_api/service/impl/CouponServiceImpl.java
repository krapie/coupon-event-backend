package com.krapi.coupon_api.service.impl;

import com.krapi.coupon_api.dao.CouponWallet;
import com.krapi.coupon_api.dto.CouponWalletDto;
import com.krapi.coupon_api.dto.response.CouponStatusGetAllResponseDto;
import com.krapi.coupon_api.exception.CouponAlreadyIssuedException;
import com.krapi.coupon_api.exception.CouponNotAvailableException;
import com.krapi.coupon_api.global.type.CouponCode;
import com.krapi.coupon_api.repository.CouponRepository;
import com.krapi.coupon_api.repository.CouponWalletRepository;
import com.krapi.coupon_api.service.CouponService;
import com.krapi.coupon_api.service.RedissonService;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RAtomicLongReactive;
import org.redisson.api.RLockReactive;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.concurrent.TimeUnit;


@RequiredArgsConstructor
@Service
public class CouponServiceImpl implements CouponService {

    private final CouponRepository couponRepository;
    private final CouponWalletRepository couponWalletRepository;
    private final RedissonService redissonService;

    public final static int LOCK_WAIT_TIME = 1000;
    public final static int LOCK_LEASE_TIME = 1000;

    @Override
    public Mono<CouponWalletDto> issueCoupon(String userId, String code) {
        RLockReactive lock = redissonService.getLock(code);

        return lock.tryLock(LOCK_WAIT_TIME, LOCK_LEASE_TIME, TimeUnit.MILLISECONDS)
                .flatMap(res -> checkStatusAndIssueCoupon(userId, code))
                .doFinally(status -> lock.unlock());
    }

    private Mono<CouponWalletDto> checkStatusAndIssueCoupon(String userId, String code) {
        RAtomicLongReactive countMono = redissonService.getAtomicCount(code);

        return countMono.getAndIncrement().flatMap(count -> {
            if (count >= CouponCode.of(code).getTotalAmount())
                return Mono.error(new CouponNotAvailableException());
            else
                return couponWalletRepository.countByCouponCodeAndUserId(code, userId).flatMap(possession -> {
                    if (possession >= CouponCode.of(code).getMaxAmountPerUser())
                        return Mono.error(new CouponAlreadyIssuedException());
                    else
                        return couponWalletRepository.save(new CouponWallet(userId, code)).flatMap(couponWallet ->
                                Mono.just(new CouponWalletDto(couponWallet))
                        );
                });
        });
    }

    @Override
    public Mono<CouponWalletDto> getCoupon(String id) {
        return couponWalletRepository.findByUuid(id)
                .map(CouponWalletDto::new);
    }

    @Override
    public Mono<CouponStatusGetAllResponseDto> getAllCouponStatus(PageRequest pageRequest) {
        return couponRepository.findAllBy(pageRequest)
                .map(coupon -> {
                    RAtomicLongReactive countMono = redissonService.getAtomicCount(coupon.getCode());
                    coupon.updateLeftAmount(countMono.get().block());
                    couponRepository.save(coupon);
                    return coupon;
                })
                .collectList()
                .zipWith(couponRepository.count())
                .map(output -> new PageImpl<>(output.getT1(), pageRequest, output.getT2()))
                .map(CouponStatusGetAllResponseDto::new);
    }
}
