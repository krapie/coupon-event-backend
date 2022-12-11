package com.krapi.coupon_api.repository;

import com.krapi.coupon_api.dao.CouponWallet;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface CouponWalletRepository extends ReactiveCrudRepository<CouponWallet, Long> {

    Mono<CouponWallet> findByUuid(String uuid);
    Mono<Long> countByCouponCodeAndUserId(String code, String userId);
    Mono<CouponWallet> findByUuidAndUserId(String uuid, String userId);
    Flux<CouponWallet> findAllByUserId(String userId, Pageable pageable);
}
