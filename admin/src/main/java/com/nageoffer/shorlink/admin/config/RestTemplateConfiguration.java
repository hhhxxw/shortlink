package com.nageoffer.shorlink.admin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * <p>
 * 功能描述: RestTemplate配置
 * </p>
 *
 * @author Hanxuewei
 * @since 2025/10/1
 */
@Configuration
public class RestTemplateConfiguration {
    
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
} 