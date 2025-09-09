package com.nageoffer.shorlink.admin.dto.req;

import lombok.Data;

/**
 * 短链接分组创建参数
 * @author 一只咸鱼的大厂梦-hxw
 * @date 2025-09-09 10:04
 */
@Data
public class ShortLinkGroupSaveReqDTO {
    /**
     * 分组名
     */
    private String name;
}
