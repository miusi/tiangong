package com.kaizhuo.core.model.dto;

import com.kaizhuo.core.error.IError;
import com.kaizhuo.core.error.SysError;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: miaochen
 * \* @Date: 2020/2/25
 * \* @Time: 14:14
 * \* To change this template use File | Settings | File Templates.
 * \* @Description: 请求响应
 * \
 */
@ApiModel("请求响应")
@Data
public class R<T> {

    private static final long serialVersionUID = -7418511416573205985L;

    @ApiModelProperty("请求结果")
    private boolean success = false;

    @ApiModelProperty("namespace")
    private String ns;
    @ApiModelProperty("响应吗")
    private int code;
    @ApiModelProperty("响应消息")
    private String msg = "success";
    @ApiModelProperty("响应结果")
    private T result;

    public R() {
    }

    public static R error() {
        return error(SysError.SYSTEM_INTERNAL_ERROR);
    }

    public static R error(IError error) {
        return error(error.getNs(), error.getCode(), error.getMsg());
    }


    public static R error(String msg){
        return error(SysError.SYSTEM_INTERNAL_ERROR.getNs(),SysError.SYSTEM_INTERNAL_ERROR.getCode(),msg);
    }

    public static R error(String ns, int code, String msg) {
        R r = new R();
        r.ns = ns;
        r.code = code;
        r.msg = msg;
        return r;
    }
}
