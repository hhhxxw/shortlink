package com.nageoffer.shorlink.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nageoffer.shorlink.admin.dao.entity.GroupDO;
import com.nageoffer.shorlink.admin.dto.req.ShortLinkGroupUpdateReqDTO;
import com.nageoffer.shorlink.admin.dto.resp.ShortLinkGroupRespDTO;

import java.util.List;

/**
 * 短链接分组接口层
 * @author 一只咸鱼的大厂梦-hxw
 * @date 2025-09-09 09:01
 */
public interface GroupService extends IService<GroupDO> {
    /**
     * 新增短链接分组
     * @param groupName
     */
    void saveGroup(String groupName);

    /**
     * 查询短链接分组列表
     * @return
     */
    List<ShortLinkGroupRespDTO> listGroup();

    /**
     * 修改短链接分组
     * @param
     */
    void updateGroup(ShortLinkGroupUpdateReqDTO requestParam);
}
