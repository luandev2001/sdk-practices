package com.xuanluan.mc.practices.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.concurrent.TimeUnit;

@EnableCaching
@Configuration
public class CacheConfig {
    @Value("${cache.primary.expire_time:7}")
    private int expireTime;
    @Value("${cache.primary.period:DAYS}")
    private String period;
    @Value("${cache.primary.maxsize:500}")
    private int maxSize;

    @Value("${cache.user.expire_time:120}")
    private int userExpireTime;
    @Value("${cache.user.period:MINUTES}")
    private String userPeriod;
    @Value("${cache.user.maxsize:10000}")
    private int userMaxSize;

    @Bean
    @Primary
    public CacheManager cacheManager() {
        CaffeineCacheManager caffeineCacheManager = new CaffeineCacheManager();
        caffeineCacheManager.setCaffeine(
                Caffeine.newBuilder()
                        .expireAfterWrite(expireTime, TimeUnit.valueOf(period.toUpperCase()))
                        .maximumSize(maxSize)
        );
        return caffeineCacheManager;
    }

    @Bean
    public CacheManager currentUserCacheManager() {
        CaffeineCacheManager caffeineCacheManager = new CaffeineCacheManager();
        caffeineCacheManager.setCaffeine(
                Caffeine.newBuilder()
                        .expireAfterWrite(userExpireTime, TimeUnit.valueOf(userPeriod.toUpperCase()))
                        .maximumSize(userMaxSize)
        );
        return caffeineCacheManager;
    }
}
