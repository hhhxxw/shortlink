package com.nageoffer.shorlink.project.common.constant;

/**
 * <p>
 * 功能描述: 短链接常量类
 * </p>
 *
 * @author Hanxuewei
 * @since 2025/10/11
 */
public class ShortLinkConstant {
    /**
     * 永久短链接默认缓存有效时间
     */
    public static final long DEFAULT_CACHE_VALID_TIME = 2629800000L;
    
    /**
     * 短链接不存在页面路径
     */
    public static final String PAGE_NOT_FOUND = "/page/notfound";
    
    /**
     * 短链接已过期页面路径
     */
    public static final String PAGE_EXPIRED = "/page/expired";
    
    /**
     * 系统错误页面路径
     */
    public static final String PAGE_ERROR = "/page/error";
}
