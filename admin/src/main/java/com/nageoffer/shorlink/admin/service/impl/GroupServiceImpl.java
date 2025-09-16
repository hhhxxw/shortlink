package com.nageoffer.shorlink.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nageoffer.shorlink.admin.common.biz.user.UserContext;
import com.nageoffer.shorlink.admin.dao.entity.GroupDO;
import com.nageoffer.shorlink.admin.dao.mapper.GroupMapper;
import com.nageoffer.shorlink.admin.dto.req.ShortLinkGroupSortReqDTO;
import com.nageoffer.shorlink.admin.dto.req.ShortLinkGroupUpdateReqDTO;
import com.nageoffer.shorlink.admin.dto.resp.ShortLinkGroupRespDTO;
import com.nageoffer.shorlink.admin.service.GroupService;
import com.nageoffer.shorlink.admin.toolkit.RandomUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 短链接分组接口实现层
 *
 * @author 一只咸鱼的大厂梦-hxw
 * @date 2025-09-09 09:03
 */
@Slf4j
@Service
public class GroupServiceImpl extends ServiceImpl<GroupMapper, GroupDO> implements GroupService {


    @Override
    public void saveGroup(String groupName) {
        String gid;
        while (true) {
            gid = RandomUtil.generateRandom(6);
            if (!hasGid(gid)) {
                break;
            }
        }
        GroupDO groupDO = GroupDO.builder()
                .gid(gid)
                .sortOrder(0)
                .username(UserContext.getUsername())
                .name(groupName)
                .build();
        baseMapper.insert(groupDO);
    }

    @Override
    public List<ShortLinkGroupRespDTO> listGroup() {
        LambdaQueryWrapper<GroupDO> queryWrapper = Wrappers.lambdaQuery(GroupDO.class)
                .eq(GroupDO::getDelFlag, 0)
                .eq(GroupDO::getUsername, UserContext.getUsername())
                .orderByDesc(GroupDO::getSortOrder, GroupDO::getUpdateTime);
        List<GroupDO> groupDoList = baseMapper.selectList(queryWrapper);
        return BeanUtil.copyToList(groupDoList, ShortLinkGroupRespDTO.class);
    }

    @Override
    public void updateGroup(ShortLinkGroupUpdateReqDTO requestParam) {
        LambdaUpdateWrapper<GroupDO> updateWrapper = Wrappers.lambdaUpdate(GroupDO.class)
                .eq(GroupDO::getUsername, UserContext.getUsername())
                .eq(GroupDO::getGid, requestParam.getGid())
                .eq(GroupDO::getDelFlag, 0);
        GroupDO groupDO = new GroupDO();
        groupDO.setName(requestParam.getName());
        baseMapper.update(groupDO, updateWrapper);
    }


    @Override
    public void deleteGroup(String gid) {
        LambdaUpdateWrapper<GroupDO> updateWrapper = Wrappers.lambdaUpdate(GroupDO.class)
                .eq(GroupDO::getUsername, UserContext.getUsername())
                .eq(GroupDO::getGid, gid)
                .eq(GroupDO::getDelFlag, 0);
        GroupDO groupDO = new GroupDO();
        groupDO.setDelFlag(1);
        baseMapper.update(groupDO, updateWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void sortGroup(List<ShortLinkGroupSortReqDTO> requestParam) {
        // Lambda表达式（函数式编程） + Mp的条件构造器
        // 遍历分组列表，foreach + lambda表达式
        requestParam.forEach(each -> {
            // 构建更新的实体
            GroupDO groupDO = GroupDO.builder()
                    // 更新的排序值
                    .sortOrder(each.getSortOrder())
                    .build();
            // 根据mp的LambdaUpdateWrapper构建更新条件
            LambdaUpdateWrapper<GroupDO> updateWrapper = Wrappers.lambdaUpdate(GroupDO.class)
                    // 根据用户名进行筛选
                    .eq(GroupDO::getUsername, UserContext.getUsername())
                    // 根据分组进行筛选
                    .eq(GroupDO::getGid, each.getGid())
                    // 只保留更新未删除的记录
                    .eq(GroupDO::getDelFlag, 0);
            // 执行更新
            baseMapper.update(groupDO, updateWrapper);
        });
    }

    private boolean hasGid(String gid) {
        LambdaQueryWrapper<GroupDO> queryWrapper = Wrappers.lambdaQuery(GroupDO.class)
                .eq(GroupDO::getGid, gid)
                .eq(GroupDO::getUsername, UserContext.getUsername());
        GroupDO hasGroupFlag = baseMapper.selectOne(queryWrapper);
        return hasGroupFlag != null;
    }

}