package com.nageoffer.shorlink.project.dto.req;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * <p>
 * 功能描述: 修改短链请求对象
 * </p>
 *
 * @author Hanxuewei
 * @since 2025/9/19
 */
@Data
public class ShortLinkUpdateReqDTO {
    /**
     * ID
     */
    private Long id;

    /**
     * 域名
     */
    private String domain;

    /**
     * 原始链接
     */
    private String originUrl;

    /**
     * 完整短链接
     */
    private String fullShortUrl;

    /**
     * 原始分组标识（用于定位记录）
     */
    private String originalGid;
    
    /**
     * 分组标识（目标分组）
     */
    private String gid;

    /**
     * 有效期类型 0：永久有效 1：用户自定义
     */
    private Integer validDateType;

    /**
     * 有效期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date validDate;

    /**
     * 描述
     */
    private String describe;
}
