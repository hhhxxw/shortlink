package com.nageoffer.shorlink.project.controller;

import com.nageoffer.shorlink.project.common.convention.result.Result;
import com.nageoffer.shorlink.project.common.convention.result.Results;
import com.nageoffer.shorlink.project.dto.req.ShortLinkCreateReqDTO;
import com.nageoffer.shorlink.project.dto.resp.ShortLinkCreateRespDTO;
import com.nageoffer.shorlink.project.service.ShortLinkService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
    @PostMapping("/api/short-link/v1/create")
    public Result<ShortLinkCreateRespDTO> createShortLink(@RequestBody ShortLinkCreateReqDTO requestParam){
        return Results.success(shortLinkService.createShortLink(requestParam));
    }
}
