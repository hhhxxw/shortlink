package com.nageoffer.shorlink.project.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.nageoffer.shorlink.project.common.convention.result.Result;
import com.nageoffer.shorlink.project.common.convention.result.Results;
import com.nageoffer.shorlink.project.dto.req.ShortLinkCreateReqDTO;
import com.nageoffer.shorlink.project.dto.req.ShortLinkGroupCountReqDTO;
import com.nageoffer.shorlink.project.dto.req.ShortLinkPageReqDTO;
import com.nageoffer.shorlink.project.dto.resp.ShortLinkCreateRespDTO;
import com.nageoffer.shorlink.project.dto.resp.ShortLinkGroupCountRespDTO;
import com.nageoffer.shorlink.project.dto.resp.ShortLinkPageRespDTO;
import com.nageoffer.shorlink.project.service.ShortLinkService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 功能描述: 短链接控制层
 * </p>
 *
 * @author Hanxuewei
 * @since 2025/9/19
 */
@RestController
@RequiredArgsConstructor
public class ShortLinkController {
    private final ShortLinkService shortLinkService;

    /**
     * 创建短链接
     */
    @PostMapping("/api/short-link/v1/create")
    public Result<ShortLinkCreateRespDTO> createShortLink(@RequestBody ShortLinkCreateReqDTO requestParam){
        return Results.success(shortLinkService.createShortLink(requestParam));
    }

    /**
     * 分页查询短链接
     */
    @GetMapping("/api/short-link/v1/page")
    public Result<IPage<ShortLinkPageRespDTO>> pageShortLink(ShortLinkPageReqDTO requestParam) {
        return Results.success(shortLinkService.pageShortLink(requestParam));
    }

    /**
     * 统计短链接数量
     */
    @PostMapping("/api/short-link/v1/count")
    public Result<List<ShortLinkGroupCountRespDTO>> countByGidList(@RequestBody ShortLinkGroupCountReqDTO requestParam) {
        return Results.success(shortLinkService.countByGidList(requestParam.getGidList()));
    }
}
