package com.nageoffer.shorlink.admin.remote.dto.resp;

import lombok.Data;
import java.util.List;

/**
 * <p>
 * 功能描述: 分页结果
 * </p>
 *
 * @author Hanxuewei
 * @since 2025/10/1
 */
@Data
public class ShortLinkPageResult {
    
    /**
     * 数据列表
     */
    private List<ShortLinkPageRespDTO> records;
    
    /**
     * 总记录数
     */
    private Long total;
    
    /**
     * 每页显示条数
     */
    private Long size;
    
    /**
     * 当前页码
     */
    private Long current;
    
    /**
     * 总页数
     */
    private Long pages;
} 