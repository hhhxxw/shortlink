package com.nageoffer.shorlink.project.common.biz.user;

import cn.hutool.core.util.StrUtil;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * 用户信息传输过滤器
 * 作用：从请求Header中获取username，放入UserContext
 */
@Slf4j
public class UserTransmitFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) 
            throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        
        // 从Header中获取username
        String username = httpServletRequest.getHeader("username");
        
        if (StrUtil.isNotBlank(username)) {
            UserContext.setUser(username);
            log.debug("接收到用户信息 - username: {}", username);
        }
        
        try {
            filterChain.doFilter(servletRequest, servletResponse);
        } finally {
            UserContext.removeUser();
        }
    }
}

