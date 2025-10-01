package com.nageoffer.shorlink.admin.remote.dto.req;

import lombok.Data;

/**
 * <p>
 * 功能描述: 分页查询的请求参数，这里是分组之后的短链接进行分页查询
 * </p>
 *
 * @author Hanxuewei
 * @since 2025/9/27
 */
@Data
public class ShortLinkPageReqDTO {
    /**
     * 分组标识
     */
    private String gid;
    
    /**
     * 当前页码
     */
    private Long current;
    
    /**
     * 每页显示条数
     */
    private Long size;
}
