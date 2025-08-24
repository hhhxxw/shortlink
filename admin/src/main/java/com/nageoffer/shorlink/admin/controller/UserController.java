package com.nageoffer.shorlink.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 一只咸鱼的大厂梦-hxw
 * @date 2025-08-24 10:49
 */

/**
 * 用户管理控制层
 */
@RestController
public class UserController {
    /**
     * 根据用户名查询用户信息
     */
    @GetMapping("/api/shortlink/v1/user/{username}")
    public String getUserByUsername(@PathVariable("username") String username){
        return "hi " + username;
    }
}
