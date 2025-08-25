package com.nageoffer.shorlink.admin.dto.resp;

import lombok.Data;

/**
 * 用户返回参数响应
 * @author 一只咸鱼的大厂梦-hxw
 * @date 2025-08-25 14:15
 */
@Data
public class UserRespDTO {
    /**
     * id
     */
    private long id;
    /**
     * 用户名
     */
    private String username;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 邮箱
     */
    private String mail;
}
