package com.nageoffer.shorlink.project.config;

import com.nageoffer.shorlink.project.common.constant.RedisKeyConstant;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RedissonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 布隆过滤器配置
 */
@Configuration
public class RBloomFilterConfiguration {

    /**
     * 防止短链接创建查询数据库的布隆过滤器
     * 预期元素数量：1亿
     * 误判率：0.001（千分之一）
     */
    @Bean
    public RBloomFilter<String> shortUriCreateCachePenetrationBloomFilter(RedissonClient redissonClient) {
        RBloomFilter<String> cachePenetrationBloomFilter = 
            redissonClient.getBloomFilter(RedisKeyConstant.SHORT_LINK_CREATE_BLOOM_FILTER);
        cachePenetrationBloomFilter.tryInit(100000000L, 0.001);
        return cachePenetrationBloomFilter;
    }
}