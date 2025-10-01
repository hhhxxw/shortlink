package com.nageoffer.shorlink.admin.remote.dto;

import com.nageoffer.shorlink.admin.common.convention.result.Result;
import com.nageoffer.shorlink.admin.remote.dto.req.ShortLinkCreateReqDTO;
import com.nageoffer.shorlink.admin.remote.dto.req.ShortLinkPageReqDTO;
import com.nageoffer.shorlink.admin.remote.dto.resp.ShortLinkCreateRespDTO;
import com.nageoffer.shorlink.admin.remote.dto.resp.ShortLinkPageResult;

/**
 * <p>
 * 功能描述: 短链接中台远程调用服务
 * </p>
 *
 * @author Hanxuewei
 * @since 2025/9/30
 */
public interface ShortLinkRemoteService {
    
    /**
     * 创建短链接
     */
    Result<ShortLinkCreateRespDTO> createShortLink(ShortLinkCreateReqDTO requestParam);
    
    /**
     * 分页查询短链接
     */
    Result<ShortLinkPageResult> pageShortLink(ShortLinkPageReqDTO requestParam);
}
