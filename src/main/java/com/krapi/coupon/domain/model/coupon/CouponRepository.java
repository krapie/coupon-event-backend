package com.krapi.coupon.domain.model.coupon;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import reactor.core.publisher.Flux;

public interface CouponRepository extends ReactiveSortingRepository<Coupon, Long> {

    Flux<Coupon> findAllBy(Pageable pageable);
}
