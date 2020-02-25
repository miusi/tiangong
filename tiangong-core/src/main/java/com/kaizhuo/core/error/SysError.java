package com.kaizhuo.core.error;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: miaochen
 * \* @Date: 2020/2/25
 * \* @Time: 14:30
 * \* To change this template use File | Settings | File Templates.
 * \* @Description:
 * \
 */
public enum  SysError implements IError {
    SYSTEM_INTERNAL_ERROR(500, "系统内部错误"),
    INVALID_PARAMETER(1, "无效参数"),
    SERVICE_NOT_FOUND(2, "服务不存在"),
    PARAMETER_REQUIRED(3, "参数不全"),
    PARAMETER_MAX_LENGTH(4, "参数过长"),
    PARAMETER_MIN_LENGTH(5, "参数过短"),
    PARAMETER_ANNOTATION_NOT_MATCH(6, "参数出错"),
    PARAMETER_NOT_MATCH_RULE(7, "参数验证失败"),
    METHOD_NOT_SUPPORTED(8, "请求方法出错"),
    CONTENT_TYPE_NOT_SUPPORT(9, "不支持的content类型"),
    JSON_FORMAT_ERROR(10, "json格式化出错"),
    CALL_REMOTE_ERROR(11, "远程调用出错");
    private static final String ns = "SYS";
    int code;
    String msg;

    SysError(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public String getNs() {
        return null;
    }

    @Override
    public int getCode() {
        return 0;
    }

    @Override
    public String getMsg() {
        return null;
    }
}
