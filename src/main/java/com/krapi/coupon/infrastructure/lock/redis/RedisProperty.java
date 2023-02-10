package com.krapi.coupon.infrastructure.lock.redis;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@ConfigurationProperties("spring.redis")
@Component
public class RedisProperty {

    private String host;
    private int port;
}
