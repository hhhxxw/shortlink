package com.nageoffer.shorlink.admin.remote.dto.req;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 批量查询分组短链接数量请求参数
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShortLinkGroupCountReqDTO {
    /**
     * 分组标识列表
     */
    private List<String> gidList;
} 