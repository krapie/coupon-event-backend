package com.krapi.coupon.application;

import com.krapi.coupon.domain.model.coupon.Coupon;
import com.krapi.coupon.domain.model.wallet.CouponWallet;
import com.krapi.coupon.domain.model.coupon.CouponCode;
import com.krapi.coupon.domain.model.coupon.CouponRepository;
import com.krapi.coupon.domain.model.wallet.CouponWalletRepository;
import com.krapi.coupon.infrastructure.uuid.UuidService;
import com.krapi.coupon.infrastructure.lock.LockService;
import org.redisson.api.RAtomicLongReactive;
import org.redisson.api.RLockReactive;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.concurrent.TimeUnit;


@RequiredArgsConstructor
@Service
public class CouponService {

    private final CouponRepository couponRepository;
    private final CouponWalletRepository couponWalletRepository;
    private final LockService lockService;
    private final UuidService uuidService;

    public final static int LOCK_WAIT_TIME = 1000;
    public final static int LOCK_LEASE_TIME = 1000;

    public Mono<CouponWalletDto> issueCoupon(String userId, String code) {
        RLockReactive lock = lockService.getLock(code);

        return lock.tryLock(LOCK_WAIT_TIME, LOCK_LEASE_TIME, TimeUnit.MILLISECONDS)
                .flatMap(res -> checkStatusAndIssueCoupon(userId, code))
                .doFinally(status -> lock.unlock());
    }

    private Mono<CouponWalletDto> checkStatusAndIssueCoupon(String userId, String code) {
        RAtomicLongReactive countMono = lockService.getAtomicCount(code);

        return countMono.getAndIncrement().flatMap(count -> {
            if (count >= CouponCode.of(code).getTotalAmount())
                return Mono.error(new CouponNotAvailableException());
            else
                return couponWalletRepository.countByCouponCodeAndUserId(code, userId).flatMap(possession -> {
                    if (possession >= CouponCode.of(code).getMaxAmountPerUser())
                        return Mono.error(new CouponAlreadyIssuedException());
                    else
                        return couponWalletRepository.save(new CouponWallet(userId, code, uuidService.generate()))
                                .flatMap(couponWallet -> Mono.just(new CouponWalletDto(couponWallet)));
                });
        });
    }

    public Mono<CouponWalletDto> getCoupon(String id) {
        return couponWalletRepository.findByUuid(id)
                .map(CouponWalletDto::new);
    }

    public Mono<Page<Coupon>> getAllCouponStatus(PageRequest pageRequest) {
        return couponRepository.findAllBy(pageRequest)
                .map(coupon -> {
                    RAtomicLongReactive countMono = lockService.getAtomicCount(coupon.getCode());
                    coupon.updateLeftAmount(countMono.get().block());
                    couponRepository.save(coupon);
                    return coupon;
                })
                .collectList()
                .zipWith(couponRepository.count())
                .map(output -> new PageImpl<>(output.getT1(), pageRequest, output.getT2()));
    }
}
