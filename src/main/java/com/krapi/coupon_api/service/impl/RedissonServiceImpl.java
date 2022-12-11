package com.krapi.coupon_api.service.impl;

import com.krapi.coupon_api.service.RedissonService;
import org.redisson.api.RAtomicLongReactive;
import org.redisson.api.RLockReactive;
import org.redisson.api.RedissonClient;
import org.redisson.api.RedissonReactiveClient;
import org.springframework.stereotype.Service;

@Service
public class RedissonServiceImpl implements RedissonService {

    private final RedissonReactiveClient redissonReactiveClient;

    public RedissonServiceImpl(RedissonClient redissonClient) {
        this.redissonReactiveClient = redissonClient.reactive();
    }

    @Override
    public RLockReactive getLock(String lockName) {
        return redissonReactiveClient.getLock(lockName + ":lock");
    }

    @Override
    public RAtomicLongReactive getAtomicCount(String key) {
        return redissonReactiveClient.getAtomicLong(key + ":count");
    }
}
