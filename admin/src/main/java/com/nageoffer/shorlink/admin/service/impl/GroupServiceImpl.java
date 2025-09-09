package com.nageoffer.shorlink.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nageoffer.shorlink.admin.dao.entity.GroupDO;
import com.nageoffer.shorlink.admin.dao.mapper.GroupMapper;
import com.nageoffer.shorlink.admin.service.GroupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 短链接分组接口实现层
 * @author 一只咸鱼的大厂梦-hxw
 * @date 2025-09-09 09:03
 */
@Slf4j
@Service
public class GroupServiceImpl extends ServiceImpl<GroupMapper, GroupDO> implements GroupService {

}
