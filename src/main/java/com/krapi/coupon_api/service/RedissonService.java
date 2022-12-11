package com.krapi.coupon_api.service;

import org.redisson.api.RAtomicLongReactive;
import org.redisson.api.RLockReactive;

public interface RedissonService {

    RLockReactive getLock(String lockName);
    RAtomicLongReactive getAtomicCount(String key);
}
