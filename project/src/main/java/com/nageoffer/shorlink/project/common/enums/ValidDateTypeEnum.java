package com.nageoffer.shorlink.project.common.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * <p>
 * 功能描述: 有效期数据类型
 * </p>
 *
 * @author Hanxuewei
 * @since 2025/10/4
 */
@Getter
@RequiredArgsConstructor
public enum ValidDateTypeEnum {
    /**
     * 长久有效期
     */
    PERMANENT(0),


    /**
     * 自定义有效期
     */
    CUSTOM(1);

    private final int type;

}
