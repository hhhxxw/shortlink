package com.nageoffer.shorlink.project.config;

import com.nageoffer.shorlink.project.service.ShortLinkCacheWarmUpService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 功能描述: 应用启动时执行缓存预热
 * </p>
 *
 * @author Hanxuewei
 * @since 2025/10/11
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class CacheWarmUpRunner implements ApplicationRunner {
    
    private final ShortLinkCacheWarmUpService cacheWarmUpService;
    
    @Override
    public void run(ApplicationArguments args) {
        log.info("==================== 应用启动，开始缓存预热 ====================");
        
        try {
            // 预热 Top 100 热点短链接
            cacheWarmUpService.warmUpHotLinks(100);
            
            log.info("==================== 缓存预热完成 ====================");
        } catch (Exception e) {
            log.error("缓存预热失败，但不影响应用启动", e);
        }
    }
}

