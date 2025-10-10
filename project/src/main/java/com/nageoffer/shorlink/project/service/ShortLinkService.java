package com.nageoffer.shorlink.project.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.nageoffer.shorlink.project.dao.entity.ShortLinkDO;
import com.nageoffer.shorlink.project.dto.req.ShortLinkCreateReqDTO;
import com.nageoffer.shorlink.project.dto.req.ShortLinkPageReqDTO;
import com.nageoffer.shorlink.project.dto.req.ShortLinkUpdateReqDTO;
import com.nageoffer.shorlink.project.dto.resp.ShortLinkCreateRespDTO;
import com.nageoffer.shorlink.project.dto.resp.ShortLinkGroupCountRespDTO;
import com.nageoffer.shorlink.project.dto.resp.ShortLinkPageRespDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

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

    /**
     * 修改短链接
     * @param requestParam  修改短链接请求参数
     */
    void updateShortLink(ShortLinkUpdateReqDTO requestParam);

    /**
     * 分页查询短链接
     * @param requestParam 分页查询短链接请求参数
     * @return 短链接分页返回结果
     */
    IPage<ShortLinkPageRespDTO> pageShortLink(ShortLinkPageReqDTO requestParam);

    /**
     * 查询分组短链接数量
     * @param gidList 分组标识列表
     * @return 分组短链接数量列表
     */
    List<ShortLinkGroupCountRespDTO> countByGidList(List<String> gidList);

    /**
     * 短链接跳转
     * @param shortUri 短链接后缀
     * @param request Http 请求
     * @param response Http 响应
     */
    void restoreUrl(String shortUri, HttpServletRequest request, HttpServletResponse response) throws IOException;
}
