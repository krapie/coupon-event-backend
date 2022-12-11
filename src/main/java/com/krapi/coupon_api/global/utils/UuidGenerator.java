package com.krapi.coupon_api.global.utils;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class UuidGenerator {

    public static UUID generate() {
        byte[] bytes = new byte[16];
        ThreadLocalRandom.current().nextBytes(bytes);

        long mostSigBits = 0;
        for (int i = 0; i < 8; i++) {
            mostSigBits = (mostSigBits << 8) | (bytes[i] & 0xff);
        }
        long leastSigBits = 0;
        for (int i = 8; i < 16; i++) {
            leastSigBits = (leastSigBits << 8) | (bytes[i] & 0xff);
        }

        return new UUID(mostSigBits, leastSigBits);
    }
}