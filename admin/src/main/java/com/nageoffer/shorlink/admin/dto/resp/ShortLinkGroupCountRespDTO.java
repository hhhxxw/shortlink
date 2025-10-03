package com.nageoffer.shorlink.admin.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 分组短链接数量返回参数
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShortLinkGroupCountRespDTO {
    /**
     * 分组标识
     */
    private String gid;
    
    /**
     * 短链接数量
     */
    private Integer shortLinkCount;
} 