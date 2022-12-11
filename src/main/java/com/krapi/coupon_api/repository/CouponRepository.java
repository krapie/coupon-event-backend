package com.krapi.coupon_api.repository;

import com.krapi.coupon_api.dao.Coupon;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import reactor.core.publisher.Flux;

public interface CouponRepository extends ReactiveSortingRepository<Coupon, Long> {

    Flux<Coupon> findAllBy(Pageable pageable);
}
