package com.kaizhuo.component.rbac.error;

import com.kaizhuo.core.error.IError;

/**
 * @program: tiangong
 * @package: com.kaizhuo.component.rbac.error
 * @description: RBAC 错误
 * @author: miaochen
 * @create: 2020-02-27 23:25
 * @copyright: CopyRight (c) 2020
 * @version: 1.0.0
 * @modified: miaochen
 **/
public enum RbacError implements IError {
    USER_EXISTED(1, "用户已存在"),
    DEPT_EXISTED(2, "部门已存在"),
    MENU_EXISTED(3, "目录已存在"),
    ROLE_EXISTED(4, "角色已存在"),
    ;
    int code;
    String msg;
    private static final String ns = "RBAC";

    RbacError(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getNs() {
        return ns;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
