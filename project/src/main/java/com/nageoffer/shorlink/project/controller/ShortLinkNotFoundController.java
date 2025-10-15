package com.nageoffer.shorlink.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>
 * 功能描述: 短链接错误页面控制器
 * </p>
 *
 * @author Hanxuewei
 * @since 2025/10/15
 */
@Controller
@RequestMapping("/page")
public class ShortLinkNotFoundController {

    /**
     * 短链接不存在跳转页面
     * @return 404 错误页面
     */
    @GetMapping("/notfound")
    public String notfound() {
        return "notfound";
    }

    /**
     * 短链接已过期跳转页面
     * @return 过期提示页面
     */
    @GetMapping("/expired")
    public String expired() {
        return "expired";
    }

    /**
     * 系统错误跳转页面
     * @return 错误提示页面
     */
    @GetMapping("/error")
    public String error() {
        return "error";
    }
}
