package com.nageoffer.shorlink.project.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.text.StrBuilder;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nageoffer.shorlink.project.common.convention.exception.ClientException;
import com.nageoffer.shorlink.project.common.convention.exception.ServiceException;
import com.nageoffer.shorlink.project.common.enums.ValidDateTypeEnum;
import com.nageoffer.shorlink.project.dao.entity.ShortLinkDO;
import com.nageoffer.shorlink.project.dao.mapper.ShortLinkMapper;
import com.nageoffer.shorlink.project.dto.req.ShortLinkCreateReqDTO;
import com.nageoffer.shorlink.project.dto.req.ShortLinkPageReqDTO;
import com.nageoffer.shorlink.project.dto.req.ShortLinkUpdateReqDTO;
import com.nageoffer.shorlink.project.dto.resp.ShortLinkCreateRespDTO;
import com.nageoffer.shorlink.project.dto.resp.ShortLinkGroupCountRespDTO;
import com.nageoffer.shorlink.project.dto.resp.ShortLinkPageRespDTO;
import com.nageoffer.shorlink.project.service.ShortLinkService;
import com.nageoffer.shorlink.project.toolkit.HashUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBloomFilter;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

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
        
        // 处理有效期：如果是永久有效，设置为MySQL支持的最大日期
        Date validDate;
        if (requestParam.getValidDateType() != null && 
            requestParam.getValidDateType() == ValidDateTypeEnum.PERMANENT.getType()) {
            // 永久有效：设置为9999-12-31 23:59:59
            validDate = new Date(253402271999000L);  // 9999-12-31 23:59:59
        } else {
            // 自定义有效期：使用传入的日期
            validDate = requestParam.getValidDate();
        }
        
        // 组装ShortLinkDO, insert入库
        ShortLinkDO shortLinkDO = ShortLinkDO.builder()
                .domain(requestParam.getDomain())
                .originUrl(requestParam.getOriginUrl())
                .gid(requestParam.getGid())
                .createdType(requestParam.getCreatedType())
                .validDateType(requestParam.getValidDateType())
                .validDate(validDate)
                .describe(requestParam.getDescribe())
                .shortUri(shortLinkSuffix)
                .enableStatus(1)  // 改为创建时就启用
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
        // select * from t_link where gid = ? and enable_status = 1 and delFlag = 0 order by create_time desc;
        LambdaQueryWrapper<ShortLinkDO> queryWrapper = Wrappers.lambdaQuery(ShortLinkDO.class)
                .eq(ShortLinkDO::getGid, requestParam.getGid())
                .eq(ShortLinkDO::getEnableStatus, 1)  // 改为查询已启用的
                .eq(ShortLinkDO::getDelFlag, 0)
                .orderByDesc(ShortLinkDO::getCreateTime);
        IPage<ShortLinkDO> resultPage = baseMapper.selectPage(requestParam, queryWrapper);
        return resultPage.convert(each -> BeanUtil.toBean(each, ShortLinkPageRespDTO.class));
    }

    @Override
    public List<ShortLinkGroupCountRespDTO> countByGidList(List<String> gidList) {
        // 输出参数
        log.info("接受到的批量查询请求, gidList: {}", gidList);

        LambdaQueryWrapper<ShortLinkDO> queryWrapper = Wrappers.lambdaQuery(ShortLinkDO.class)
                .select(ShortLinkDO::getGid)
                .in(ShortLinkDO::getGid, gidList)
                .eq(ShortLinkDO::getEnableStatus, 1)
                .eq(ShortLinkDO::getDelFlag, 0);
        
        List<ShortLinkDO> shortLinkDOList = baseMapper.selectList(queryWrapper);
        log.info("查询到的短链接数量：{}", shortLinkDOList.size());
        log.info("短链接列表: {}", shortLinkDOList);
        // 按 gid 分组统计数量
        Map<String, Long> gidCountMap = shortLinkDOList.stream()
                .collect(Collectors.groupingBy(ShortLinkDO::getGid, Collectors.counting()));
        log.info("分组统计结果: {}", gidCountMap);
        // 转换为响应DTO，确保所有gid都有返回值（即使数量为0）
        List<ShortLinkGroupCountRespDTO> result = gidList.stream()
                .map(gid -> ShortLinkGroupCountRespDTO.builder()
                        .gid(gid)
                        .shortLinkCount(gidCountMap.getOrDefault(gid, 0L).intValue())
                        .build())
                .collect(Collectors.toList());
        log.info("最终返回结果: {}", result);
        return result;
    }

    /**
     * 修改短链接
     * @param requestParam  修改短链接请求参数
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateShortLink(ShortLinkUpdateReqDTO requestParam) {
        log.info("修改短链接开始，id: {}", requestParam.getId());


        if(requestParam.getId() == null){
            throw new ServiceException("短链接ID不能为空");
        }

        if (requestParam.getGid() == null || requestParam.getGid().trim().isEmpty()) {
            throw new ServiceException("分组标识不能为空");
        }
        
        if (requestParam.getOriginalGid() == null || requestParam.getOriginalGid().trim().isEmpty()) {
            throw new ServiceException("原始分组标识不能为空");
        }

        if (requestParam.getFullShortUrl() == null || requestParam.getFullShortUrl().trim().isEmpty()) {
            throw new ServiceException("完整短链接不能为空");
        }

        // 1. 查询现有短链接记录（使用分片键）
        // ⚠️ 关键：必须使用 originalGid 查询，因为 gid 是分片键
        // 使用 originalGid + fullShortUrl 作为查询条件，只查询未删除的记录
        // select * from t_link t where gid = ? and full_short_url = ? and del_flag = 0
        log.info("查询短链接，originalGid: {}, fullShortUrl: {}", requestParam.getOriginalGid(), requestParam.getFullShortUrl());
        LambdaQueryWrapper<ShortLinkDO> queryWrapper = Wrappers.lambdaQuery(ShortLinkDO.class)
                .eq(ShortLinkDO::getGid, requestParam.getOriginalGid())  // 使用原始gid定位记录
                .eq(ShortLinkDO::getFullShortUrl, requestParam.getFullShortUrl())
                .eq(ShortLinkDO::getDelFlag, 0);
        ShortLinkDO hasShortLinkDO = baseMapper.selectOne(queryWrapper);

        // 检查短链接是否存在
        if(hasShortLinkDO == null){
            log.error("短链接记录不存在，originalGid: {}, fullShortUrl: {}", requestParam.getOriginalGid(), requestParam.getFullShortUrl());
            throw new ClientException("短链接记录不存在");
        }
        
        log.info("查询到短链接，id: {}, originalGid: {}, targetGid: {}", hasShortLinkDO.getId(), hasShortLinkDO.getGid(), requestParam.getGid());

        if(!Objects.equals(hasShortLinkDO.getId(), requestParam.getId())){
            throw new ServiceException("短链接ID不匹配，可能存在数据不一致");
        }

        // 构建更新对象
        // 设计思想：
        // 不可变字段：从原记录中复制
        // 可变字段：从请求参数中获取
        
        // 处理有效期：如果是永久有效，设置为MySQL支持的最大日期
        Date validDate;
        if (requestParam.getValidDateType() != null && 
            requestParam.getValidDateType() == ValidDateTypeEnum.PERMANENT.getType()) {
            // 永久有效：设置为9999-12-31 23:59:59
            validDate = new Date(253402271999000L);  // 9999-12-31 23:59:59
        } else {
            // 自定义有效期：使用传入的日期
            validDate = requestParam.getValidDate();
        }
        
        ShortLinkDO shortLinkDO = ShortLinkDO.builder()
                .id(hasShortLinkDO.getId())
                .domain(hasShortLinkDO.getDomain())
                .shortUri(hasShortLinkDO.getShortUri())
                .clickNum(hasShortLinkDO.getClickNum())
                .favicon(hasShortLinkDO.getFavicon())
                .createdType(hasShortLinkDO.getCreatedType())
                .gid(requestParam.getGid())
                .originUrl(requestParam.getOriginUrl())
                .describe(requestParam.getDescribe())
                .validDateType(requestParam.getValidDateType())
                .validDate(validDate)
                .build();
        // 根据gid是否变化，采用不同策略

        if(Objects.equals(requestParam.getOriginalGid(), requestParam.getGid())){
            // gid没有变化直接更新（originalGid == gid）
            log.info("gid没有变化，直接更新，id: {}, gid: {}", requestParam.getId(), requestParam.getGid());
            LambdaUpdateWrapper<ShortLinkDO> updateWrapper = Wrappers.lambdaUpdate(ShortLinkDO.class)
                    .eq(ShortLinkDO::getId, requestParam.getId())
                    .eq(ShortLinkDO::getGid, requestParam.getOriginalGid())  // 使用原始gid定位记录
                    .eq(ShortLinkDO::getDelFlag, 0);
            int updateRows = baseMapper.update(shortLinkDO, updateWrapper);
            if(updateRows == 0){
                throw new ServiceException("更新短链接失败");
            }
        } else {
            // gid已经变化，删除旧记录，插入新记录
            log.info("gid 已变化，执行删除+插入操作，旧gid: {}, 新gid：{}", requestParam.getOriginalGid(), requestParam.getGid());
            LambdaUpdateWrapper<ShortLinkDO> deleteWrapper = Wrappers.lambdaUpdate(ShortLinkDO.class)
                    .eq(ShortLinkDO::getId,requestParam.getId())
                    .eq(ShortLinkDO::getGid, requestParam.getOriginalGid())  // 使用原始gid定位要删除的记录
                    .eq(ShortLinkDO::getDelFlag, 0);
            int deleteRows = baseMapper.delete(deleteWrapper);
            if(deleteRows == 0){
                throw new ServiceException("删除旧短链接记录失败");
            }

            int insertRows = baseMapper.insert(shortLinkDO);
            if(insertRows == 0){
                throw new ServiceException("插入新增短链接失败");
            }
        }
        log.info("修改短链接成功，id: {}", requestParam.getId());
    }

    /**
     * 生成短链接
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
