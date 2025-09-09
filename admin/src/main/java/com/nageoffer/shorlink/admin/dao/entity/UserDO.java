package com.nageoffer.shorlink.admin.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.nageoffer.shorlink.admin.common.database.BaseDO;
import lombok.Data;

import java.io.Serializable;

/**
 * @description t_user
 * @author zhengkai.blog.csdn.net
 * @date 2025-08-25
 */
@TableName("t_user")
@Data
public class UserDO extends BaseDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    /**
     * ID
     */
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 邮箱
     */
    private String mail;

    /**
     * 注销时间戳
     */

    private Long deletionTime;

    public UserDO() {}
}