package com.nageoffer.shorlink.admin.common.enums;

import com.nageoffer.shorlink.admin.common.convention.errorcode.IErrorCode;

/**
 * @author 一只咸鱼的大厂梦-hxw
 * @date 2025-08-28 10:25
 */
public enum UserErrorCodeEnum implements IErrorCode {

    USER_TOKEN_FAIL("A000200", "用户token验证失败"),

    USER_NULL("B000200", "用户记录不存在"),

    USER_NAME_EXIST("B000201", "用户名已经存在"),

    USER_EXIST("B000202", "用户已经存在"),

    USER_SAVE_ERROR("B000203", "用户记录新增失败")
    ;

    private final String code;

    private final String message;

    UserErrorCodeEnum(String code, String message){
        this.code = code;
        this.message = message;
    }
    @Override
    public String code() {
        return code;
    }

    @Override
    public String message() {
        return message;
    }
}
