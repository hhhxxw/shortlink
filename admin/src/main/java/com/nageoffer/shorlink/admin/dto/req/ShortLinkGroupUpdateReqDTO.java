package com.nageoffer.shorlink.admin.dto.req;

import lombok.Data;

/**
 * 修改请求分组
 * @author 一只咸鱼的大厂梦-hxw
 * @date 2025-09-09 10:04
 */
@Data
public class ShortLinkGroupUpdateReqDTO {
    /**
     * 分组标识
     */
    private String gid;
    /**
     * 分组名
     */
    private String name;
}
