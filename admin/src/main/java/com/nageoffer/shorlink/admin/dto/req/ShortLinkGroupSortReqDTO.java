package com.nageoffer.shorlink.admin.dto.req;

import lombok.Data;

/**
 * <p>
 * 功能描述: 短链接分组排序参数
 * </p>
 *
 * @author Hanxuewei
 * @since 2025/9/16
 */
@Data
public class ShortLinkGroupSortReqDTO {
    /**
     * 分组名ID
     */
    private String gid;

    /**
     * 排序
     */
    private Integer sortOrder;

}
