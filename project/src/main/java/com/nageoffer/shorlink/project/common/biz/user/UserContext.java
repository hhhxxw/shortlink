package com.nageoffer.shorlink.project.common.biz.user;

import com.alibaba.ttl.TransmittableThreadLocal;

/**
 * 用户上下文
 */
public final class UserContext {

    private static final ThreadLocal<String> USER_THREAD_LOCAL = new TransmittableThreadLocal<>();

    /**
     * 设置用户至上下文
     *
     * @param username 用户名
     */
    public static void setUser(String username) {
        USER_THREAD_LOCAL.set(username);
    }

    /**
     * 获取上下文中用户名称
     *
     * @return 用户名称
     */
    public static String getUsername() {
        return USER_THREAD_LOCAL.get();
    }

    /**
     * 清理用户上下文
     */
    public static void removeUser() {
        USER_THREAD_LOCAL.remove();
    }
}




