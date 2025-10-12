package com.nageoffer.shorlink.project.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.nageoffer.shorlink.project.common.constant.RedisKeyConstant;
import com.nageoffer.shorlink.project.dao.entity.ShortLinkDO;
import com.nageoffer.shorlink.project.dao.entity.ShortLinkGotoDO;
import com.nageoffer.shorlink.project.dao.mapper.ShortLinkGotoMapper;
import com.nageoffer.shorlink.project.dao.mapper.ShortLinkMapper;
import com.nageoffer.shorlink.project.service.ShortLinkCacheWarmUpService;
import com.nageoffer.shorlink.project.toolkit.LinkUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 功能描述: 短链接缓存预热服务实现类
 * </p>
 *
 * @author Hanxuewei
 * @since 2025/10/11
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ShortLinkCacheWarmUpServiceImpl implements ShortLinkCacheWarmUpService {
    
    private final ShortLinkMapper shortLinkMapper;
    private final ShortLinkGotoMapper shortLinkGotoMapper;
    private final StringRedisTemplate stringRedisTemplate;
    
    @Override
    public void warmUpHotLinks(int topN) {
        log.info("开始预热热点短链接，预热数量：{}", topN);
        
        try {
            // 1. 查询热点短链接（按访问量排序）
            List<ShortLinkDO> hotLinks = shortLinkMapper.selectHotLinks(topN);
            
            if (hotLinks == null || hotLinks.isEmpty()) {
                log.warn("未查询到热点短链接，跳过预热");
                return;
            }
            
            log.info("查询到 {} 条热点短链接，开始缓存", hotLinks.size());
            
            // 2. 逐个加载到缓存
            int successCount = 0;
            for (ShortLinkDO shortLink : hotLinks) {
                try {
                    warmUpSingleLinkInternal(shortLink);
                    successCount++;
                } catch (Exception e) {
                    log.error("预热短链接失败：{}", shortLink.getFullShortUrl(), e);
                }
            }
            
            log.info("缓存预热完成，成功：{}，失败：{}", successCount, hotLinks.size() - successCount);
            
        } catch (Exception e) {
            log.error("缓存预热异常", e);
        }
    }
    
    @Override
    public void warmUpSingleLink(String fullShortUrl) {
        log.info("开始预热单个短链接：{}", fullShortUrl);
        
        try {
            // 1. 查询路由表获取 gid
            LambdaQueryWrapper<ShortLinkGotoDO> gotoQueryWrapper = Wrappers.lambdaQuery(ShortLinkGotoDO.class)
                    .eq(ShortLinkGotoDO::getFullShortUrl, fullShortUrl);
            ShortLinkGotoDO shortLinkGotoDO = shortLinkGotoMapper.selectOne(gotoQueryWrapper);
            
            if (shortLinkGotoDO == null) {
                log.warn("路由表中未找到短链接：{}", fullShortUrl);
                return;
            }
            
            // 2. 根据 gid 查询短链接详情
            LambdaQueryWrapper<ShortLinkDO> queryWrapper = Wrappers.lambdaQuery(ShortLinkDO.class)
                    .eq(ShortLinkDO::getGid, shortLinkGotoDO.getGid())
                    .eq(ShortLinkDO::getFullShortUrl, fullShortUrl)
                    .eq(ShortLinkDO::getEnableStatus, 1)
                    .eq(ShortLinkDO::getDelFlag, 0);
            
            ShortLinkDO shortLinkDO = shortLinkMapper.selectOne(queryWrapper);
            
            if (shortLinkDO == null) {
                log.warn("数据库中未找到短链接：{}", fullShortUrl);
                return;
            }
            
            // 3. 写入缓存
            warmUpSingleLinkInternal(shortLinkDO);
            log.info("预热单个短链接成功：{}", fullShortUrl);
            
        } catch (Exception e) {
            log.error("预热单个短链接失败：{}", fullShortUrl, e);
        }
    }
    
    /**
     * 内部方法：将短链接写入缓存
     * @param shortLinkDO 短链接对象
     */
    private void warmUpSingleLinkInternal(ShortLinkDO shortLinkDO) {
        String cacheKey = RedisKeyConstant.getShortLinkCacheKey(shortLinkDO.getFullShortUrl());
        String jsonValue = JSON.toJSONString(shortLinkDO);
        
        // 使用 LinkUtil 计算缓存有效期
        long cacheValidTime = LinkUtil.getLinkCacheValidDate(shortLinkDO.getValidDate());
        
        // 写入 Redis
        stringRedisTemplate.opsForValue().set(
            cacheKey, 
            jsonValue, 
            cacheValidTime, 
            TimeUnit.MILLISECONDS
        );
        
        log.debug("缓存预热 - 短链接：{}，TTL：{}ms", shortLinkDO.getFullShortUrl(), cacheValidTime);
    }
}

