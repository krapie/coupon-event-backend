package com.krapi.coupon.infrastructure.lock;

import org.redisson.api.RAtomicLongReactive;
import org.redisson.api.RLockReactive;

/**
 * LockService should be decoupled with any implementations
 * LockService temporary depends on Redis types, this will
 * change in further updates.
 */
public interface LockService {

    RLockReactive getLock(String lockName);
    RAtomicLongReactive getAtomicCount(String key);
}
