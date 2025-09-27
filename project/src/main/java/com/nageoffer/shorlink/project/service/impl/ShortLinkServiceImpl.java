package com.nageoffer.shorlink.project.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.text.StrBuilder;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nageoffer.shorlink.project.common.convention.exception.ServiceException;
import com.nageoffer.shorlink.project.dao.entity.ShortLinkDO;
import com.nageoffer.shorlink.project.dao.mapper.ShortLinkMapper;
import com.nageoffer.shorlink.project.dto.req.ShortLinkCreateReqDTO;
import com.nageoffer.shorlink.project.dto.req.ShortLinkPageReqDTO;
import com.nageoffer.shorlink.project.dto.resp.ShortLinkCreateRespDTO;
import com.nageoffer.shorlink.project.dto.resp.ShortLinkPageRespDTO;
import com.nageoffer.shorlink.project.service.ShortLinkService;
import com.nageoffer.shorlink.project.toolkit.HashUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBloomFilter;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 功能描述: 短链接接口实现层
 * </p>
 *
 * @author Hanxuewei
 * @since 2025/9/19
 */
@Slf4j
@Service
@AllArgsConstructor
public class ShortLinkServiceImpl extends ServiceImpl<ShortLinkMapper, ShortLinkDO> implements ShortLinkService {
    private final RBloomFilter<String> shortUriCreateCachePenetrationBloomFilter;

    @Override
    public ShortLinkCreateRespDTO createShortLink(ShortLinkCreateReqDTO requestParam) {
        // 生成短链接
        String shortLinkSuffix = generateSuffix(requestParam);
        // 拼接完整的短链接
        String fullShorUrl = StrBuilder.create(requestParam.getDomain())
                .append("/")
                .append(shortLinkSuffix)
                .toString();
        // 组装ShortLinkDO, insert入库
        ShortLinkDO shortLinkDO = ShortLinkDO.builder()
                .domain(requestParam.getDomain())
                .originUrl(requestParam.getOriginUrl())
                .gid(requestParam.getGid())
                .createdType(requestParam.getCreatedType())
                .validDateType(requestParam.getValidDateType())
                .validDate(requestParam.getValidDate())
                .describe(requestParam.getDescribe())
                .shortUri(shortLinkSuffix)
                .enableStatus(0)
                .fullShortUrl(fullShorUrl)
                .build();
        try {
            baseMapper.insert(shortLinkDO);
        }catch (DuplicateKeyException ex){
            // TODO 当发生 DuplicateKeyException 时，只是检查数据库中是否真的存在该链接，如果存在就抛异常。但没有尝试生成一个新的短链接来避免冲突。
            // 出发唯一键冲突
            // 查询数据库， 看是否真的存在
            LambdaQueryWrapper<ShortLinkDO> queryWrapper = Wrappers.lambdaQuery(ShortLinkDO.class)
                    .eq(ShortLinkDO::getFullShortUrl, fullShorUrl);
            ShortLinkDO hasShortLinkDO = baseMapper.selectOne(queryWrapper);
            // 存在抛出业务异常
            if(hasShortLinkDO != null){
                log.warn("短链接{}重复入库", fullShorUrl);
                throw new ServiceException("短链接生成重复");
            }
        }
        // TODO 等待验证，这里应该添加fullShortUrl，生成的时候判断是用的fullShortUrl？
        shortUriCreateCachePenetrationBloomFilter.add(shortLinkSuffix);
        return ShortLinkCreateRespDTO.builder()
                .fullShortUrl(shortLinkDO.getFullShortUrl())
                .originUrl(requestParam.getOriginUrl())
                .gid(requestParam.getGid())
                .build();
    }

    @Override
    public IPage<ShortLinkPageRespDTO> pageShortLink(ShortLinkPageReqDTO requestParam) {
        LambdaQueryWrapper<ShortLinkDO> queryWrapper = Wrappers.lambdaQuery(ShortLinkDO.class)
                .eq(ShortLinkDO::getGid, requestParam.getGid())
                .eq(ShortLinkDO::getEnableStatus, 0)
                .eq(ShortLinkDO::getDelFlag, 0)
                .orderByDesc(ShortLinkDO::getCreateTime);
        IPage<ShortLinkDO> resultPage = baseMapper.selectPage(requestParam, queryWrapper);
        return resultPage.convert(each -> BeanUtil.toBean(each, ShortLinkPageRespDTO.class));
    }

    /**
     * 生成给短链接
     * @param requestParam 请求参数
     * @return 返回短链接
     */
    private String generateSuffix(ShortLinkCreateReqDTO requestParam){
        // 统计尝试次数，最多尝试10次
        int customGenerateCount = 0;
        String shortUri;
        while(true){
            if(customGenerateCount > 10){
                throw new ServiceException("短链接频繁生成， 请稍后再试");
            }
            String originUrl = requestParam.getOriginUrl();
            // 增加当前毫秒数的拼接，避免冲突
            originUrl += System.currentTimeMillis();
            // 下面这个短链接生成存在一定的冲突，上面增加毫秒就是为了尽量避免冲突
            shortUri = HashUtil.hashToBase62(originUrl);
            if(! shortUriCreateCachePenetrationBloomFilter.contains(requestParam.getDomain() + "/" + shortUri)){
                break;
            }
            customGenerateCount ++;
        }
        return shortUri;

    }
}
