package com.nageoffer.shorlink.project.job;

import com.nageoffer.shorlink.project.service.ShortLinkCacheWarmUpService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 功能描述: 缓存预热定时任务
 * </p>
 *
 * @author Hanxuewei
 * @since 2025/10/11
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class CacheWarmUpScheduledJob {
    
    private final ShortLinkCacheWarmUpService cacheWarmUpService;
    
    /**
     * 每小时执行一次缓存预热
     * cron 表达式：0 0 * * * ? (每小时整点执行)
     */
    @Scheduled(cron = "0 0 * * * ?")
    public void warmUpHotLinksHourly() {
        log.info("==================== 定时任务：开始预热热点短链接 ====================");
        
        try {
            // 预热 Top 100 热点短链接
            cacheWarmUpService.warmUpHotLinks(100);
            
            log.info("==================== 定时任务：预热完成 ====================");
        } catch (Exception e) {
            log.error("定时预热失败", e);
        }
    }
    
    /**
     * 每30分钟执行一次缓存预热（可选）
     * 如果你的流量很大，可以启用这个更频繁的预热
     */
    // @Scheduled(cron = "0 */30 * * * ?")
    public void warmUpHotLinksEvery30Minutes() {
        log.info("==================== 定时任务：开始预热热点短链接（30分钟） ====================");
        
        try {
            cacheWarmUpService.warmUpHotLinks(50);
            log.info("==================== 定时任务：预热完成（30分钟） ====================");
        } catch (Exception e) {
            log.error("定时预热失败（30分钟）", e);
        }
    }
}

