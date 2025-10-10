package com.nageoffer.shorlink.admin.controller;

import com.nageoffer.shorlink.admin.common.convention.result.Result;
import com.nageoffer.shorlink.admin.common.convention.result.Results;
import com.nageoffer.shorlink.admin.remote.ShortLinkRemoteService;
import com.nageoffer.shorlink.admin.remote.dto.req.ShortLinkCreateReqDTO;
import com.nageoffer.shorlink.admin.remote.dto.req.ShortLinkPageReqDTO;
import com.nageoffer.shorlink.admin.remote.dto.req.ShortLinkUpdateReqDTO;
import com.nageoffer.shorlink.admin.remote.dto.resp.ShortLinkCreateRespDTO;
import com.nageoffer.shorlink.admin.remote.dto.resp.ShortLinkPageResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 功能描述: 短链接后管控制层
 * </p>
 *
 * @author Hanxuewei
 * @since 2025/9/30
 */
@RestController
@RequiredArgsConstructor
public class ShortLinkController {
    
    private final ShortLinkRemoteService shortLinkRemoteService;

    /**
     * 创建短链接
     */
    @PostMapping("/api/short-link/admin/v1/create")
    public Result<ShortLinkCreateRespDTO> createShortLink(@RequestBody ShortLinkCreateReqDTO requestParam) {
        return shortLinkRemoteService.createShortLink(requestParam);
    }
    
    /**
     * 分页查询短链接
     */
    @GetMapping("/api/short-link/admin/v1/page")
    public Result<ShortLinkPageResult> pageShortLink(ShortLinkPageReqDTO requestParam) {
        return shortLinkRemoteService.pageShortLink(requestParam);
    }

    /**
     * 修改短链接
     */
    @PutMapping("/api/short-link/admin/v1/update")
    public Result<Void> updateShortLink(@RequestBody ShortLinkUpdateReqDTO requestParam) {  // 新增
        shortLinkRemoteService.updateShortLink(requestParam);
        return Results.success();
    }
}
