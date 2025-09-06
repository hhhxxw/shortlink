package com.nageoffer.shorlink.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nageoffer.shorlink.admin.dao.entity.UserDO;
import com.nageoffer.shorlink.admin.dto.req.UserLoginReqDTO;
import com.nageoffer.shorlink.admin.dto.req.UserRegisterReqDTO;
import com.nageoffer.shorlink.admin.dto.req.UserUpdateReqDTO;
import com.nageoffer.shorlink.admin.dto.resp.UserLoginRespDTO;
import com.nageoffer.shorlink.admin.dto.resp.UserRespDTO;

/**
 * @author 一只咸鱼的大厂梦-hxw
 * @date 2025-08-25 14:05
 */
public interface UserService extends IService<UserDO> {

    /**
     * 根据用户名查询用户信息
     *
     * @param username 用户名
     * @return 用户返回实体
     */
    UserRespDTO getUserByUsername(String username);

    /**
     * 判断用户名是否存在
     * @param username 用户名
     * @return 用户名存在返回True，否则返回False
     */
    boolean hasUsername(String username);

    /**
     * 注册用户
     * @param requestParam 注册用户请求参数
     */
    void register(UserRegisterReqDTO requestParam);

    /**
     * 根据用户名修改用户
     * @param requestParam 修改用户名请求参数
     */
    void update( UserUpdateReqDTO requestParam);

    /**
     * 用户登陆
     * @param requestParam 用户登陆请求参数
     * @return
     */
    UserLoginRespDTO login(UserLoginReqDTO requestParam);

    /**
     * 检查用户是否登陆
     * @param token 用户登陆token
     * @return
     */
    Boolean checkLogin(String username, String token);
}
