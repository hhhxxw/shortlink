package com.nageoffer.shorlink.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nageoffer.shorlink.project.dao.entity.ShortLinkDO;
import com.nageoffer.shorlink.project.dto.req.ShortLinkCreateReqDTO;
import com.nageoffer.shorlink.project.dto.resp.ShortLinkCreateRespDTO;

/**
 * <p>
 * 功能描述: 短链接的接口层
 * </p>
 *
 * @author Hanxuewei
 * @since 2025/9/19
 */
public interface ShortLinkService extends IService<ShortLinkDO> {

    /**
     * 创建短链接
     * @param requestParam 创建短链接请求参数
     * @return 短链接创建信息
     */
    ShortLinkCreateRespDTO createShortLink(ShortLinkCreateReqDTO requestParam);
}
