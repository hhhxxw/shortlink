package com.nageoffer.shorlink.project.dto.req;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nageoffer.shorlink.project.dao.entity.ShortLinkDO;
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
public class ShortLinkPageReqDTO extends Page<ShortLinkDO> {
    /**
     * 分组标识
     */
    private String gid;
}
