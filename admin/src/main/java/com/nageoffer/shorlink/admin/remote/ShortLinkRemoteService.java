package com.nageoffer.shorlink.admin.remote;

import com.nageoffer.shorlink.admin.common.convention.result.Result;
import com.nageoffer.shorlink.admin.remote.dto.req.ShortLinkCreateReqDTO;
import com.nageoffer.shorlink.admin.remote.dto.req.ShortLinkGroupCountReqDTO;
import com.nageoffer.shorlink.admin.remote.dto.req.ShortLinkPageReqDTO;
import com.nageoffer.shorlink.admin.remote.dto.resp.ShortLinkCreateRespDTO;
import com.nageoffer.shorlink.admin.remote.dto.resp.ShortLinkGroupCountRespDTO;
import com.nageoffer.shorlink.admin.remote.dto.resp.ShortLinkPageResult;

import java.util.List;

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

    /**
     * 批量查询分组短链接数量
     * @param requestParam 查询参数
     * @return 分组短链接数量列表
     */
    Result<List<ShortLinkGroupCountRespDTO>> countByGidList(ShortLinkGroupCountReqDTO requestParam);
}
