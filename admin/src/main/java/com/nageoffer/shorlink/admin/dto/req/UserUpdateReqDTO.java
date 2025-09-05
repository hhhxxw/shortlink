package com.nageoffer.shorlink.admin.dto.req;

import lombok.Data;

/**
 * @author 一只咸鱼的大厂梦-hxw
 * @date 2025-09-05 10:41
 */
@Data
public class UserUpdateReqDTO {
    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 邮箱
     */
    private String mail;
}
