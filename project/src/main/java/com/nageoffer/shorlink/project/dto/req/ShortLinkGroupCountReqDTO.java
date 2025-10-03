package com.nageoffer.shorlink.project.dto.req;

import lombok.Data;

import java.util.List;

/**
 * 批量查询分组短链接数量请求参数
 */
@Data
public class ShortLinkGroupCountReqDTO {
    /**
     * 分组标识列表，{'15', '16'}
     */
    private List<String> gidList;
} 