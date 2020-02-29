package com.kaizhuo.security.error;

import com.kaizhuo.core.error.IError;

/**
 * @program: tiangong
 * @package: com.kaizhuo.security.error
 * @description: Security错误
 * @author: miaochen
 * @create: 2020-02-29 11:52
 * @copyright: CopyRight (c) 2020
 * @version: 1.0.0
 * @modified: miaochen
 **/
public enum SecurityError implements IError {
    ACCESS_FORBIDDEN(1, "权限不足"),
    USER_NOT_FOUNT(2, "用户不存在"),
    USER_NOT_LOGIN(3, "用户未登录"),
    LOGIN_ERROR(4, "登录失败"),
    ;

    private int code;
    private String msg;
    private static final String ns="SECURITY";

    SecurityError(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public String getNs() {
        return ns;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }
}
