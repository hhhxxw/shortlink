package com.nageoffer.shorlink.project;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author 一只咸鱼的大厂梦-hxw
 * @date 2025-08-23 22:34
 */
@SpringBootApplication
@MapperScan("com.nageoffer.shorlink.project.dao.mapper")
@EnableScheduling
public class ShortLinkApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShortLinkApplication.class, args);
    }
}
