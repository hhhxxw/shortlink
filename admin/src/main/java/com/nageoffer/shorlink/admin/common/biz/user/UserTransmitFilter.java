package com.nageoffer.shorlink.admin.common.biz.user;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.List;

/**
 * 用户信息传输过滤器
 *
 * */
@RequiredArgsConstructor
public class UserTransmitFilter implements Filter {

    private final StringRedisTemplate stringRedisTemplate;
    private static final List<String> IGNORE_URI = Lists.newArrayList(
            "/api/short-link/admin/v1/user/login",
            "/api/short-link/admin/v1/user/has-username"

    );
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String requestURI = httpServletRequest.getRequestURI();

        // 建议：使用 IGNORE_URI 列表进行判断，而不是写死一个路径，这样更灵活
        if (!IGNORE_URI.contains(requestURI)) {
            String username = httpServletRequest.getHeader("username");
            String token = httpServletRequest.getHeader("token");

            // 【关键修改】增加对 username 和 token 的非空校验
            if (StringUtils.hasText(username) && StringUtils.hasText(token)) {
                Object userInfoJsonStr = stringRedisTemplate.opsForHash().get("login_" + username, token);
                if (userInfoJsonStr != null) {
                    UserInfoDTO userInfoDTO = JSON.parseObject(userInfoJsonStr.toString(), UserInfoDTO.class);
                    UserContext.setUser(userInfoDTO);
                }
            }
        }

        try {
            filterChain.doFilter(servletRequest, servletResponse);
        } finally {
            UserContext.removeUser();
        }
    }
}
