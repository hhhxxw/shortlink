package com.nageoffer.shorlink.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nageoffer.shorlink.admin.dao.entity.UserDO;
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
}
