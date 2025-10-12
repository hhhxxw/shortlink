package com.nageoffer.shorlink.project.service;

/**
 * <p>
 * 功能描述: 短链接缓存预热服务接口
 * </p>
 *
 * @author Hanxuewei
 * @since 2025/10/11
 */
public interface ShortLinkCacheWarmUpService {
    
    /**
     * 预热热点短链接缓存
     * @param topN 预热数量（Top N）
     */
    void warmUpHotLinks(int topN);
    
    /**
     * 预热指定短链接
     * @param fullShortUrl 完整短链接
     */
    void warmUpSingleLink(String fullShortUrl);
}

