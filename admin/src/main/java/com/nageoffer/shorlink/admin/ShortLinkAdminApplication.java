package com.nageoffer.shorlink.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 一只咸鱼的大厂梦-hxw
 * @date 2025-08-23 22:34
 */
@SpringBootApplication
@MapperScan("com.nageoffer.shorlink.admin.dao.mapper")
public class ShortLinkAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShortLinkAdminApplication.class, args);
    }
}
