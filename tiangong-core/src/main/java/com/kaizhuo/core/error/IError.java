package com.kaizhuo.core.error;

public interface IError {
    /**
     * 获取nameSpace
     *
     * @return nameSpace
     */
    String getNs();

    /**
     * 获取错误码

     * @return 错误码
     */
    int getCode();

    /**
     * 获取错误信息

     * @return 错误信息
     */
    String getMsg();
}
