package com.nageoffer.shorlink.admin.dto.req;

import lombok.Data;

/**
 * 用户登陆请求参数
 * @author 一只咸鱼的大厂梦-hxw
 * @date 2025-09-06 09:36
 */
@Data
public class UserLoginReqDTO {
    /**
     * 用户名
     */
    private String username;

    /**
     * 用户密码
     */
    private String password;


}
