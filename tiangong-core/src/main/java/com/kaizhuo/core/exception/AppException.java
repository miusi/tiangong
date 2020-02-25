package com.kaizhuo.core.exception;

import com.kaizhuo.core.error.IError;
import com.kaizhuo.core.error.SysError;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: miaochen
 * \* @Date: 2020/2/25
 * \* @Time: 14:56
 * \* To change this template use File | Settings | File Templates.
 * \* @Description:
 * \
 */
public class AppException extends RuntimeException {

    private static final long serialVersionUID = -4114827135794674502L;

    private IError error;
    private String extMessage;

    public AppException() {
        this.error = SysError.SYSTEM_INTERNAL_ERROR;
        this.extMessage = null;
    }

    public AppException(String message) {
        super(message);
        this.error = SysError.SYSTEM_INTERNAL_ERROR;
        this.extMessage = null;
        this.extMessage = message;
    }

    public AppException(String message, Throwable cause) {
        super(message, cause);
        this.error = SysError.SYSTEM_INTERNAL_ERROR;
        this.extMessage = null;
        this.extMessage = message;
    }

    public AppException(Throwable cause) {
        super(cause);
        this.error = SysError.SYSTEM_INTERNAL_ERROR;
        this.extMessage = null;
    }

    public AppException(IError error) {
        this.error = SysError.SYSTEM_INTERNAL_ERROR;
        this.extMessage = null;
        this.error = error;
    }

    public AppException(String message, IError error) {
        super(message);
        this.error = SysError.SYSTEM_INTERNAL_ERROR;
        this.extMessage = message;
        this.error = error;
    }

    public AppException(String message, Throwable cause, IError error) {
        super(message, cause);
        this.error = SysError.SYSTEM_INTERNAL_ERROR;
        this.extMessage = message;
        this.error = error;
    }

    public AppException(Throwable cause, IError error) {
        super(cause);
        this.error = SysError.SYSTEM_INTERNAL_ERROR;
        this.extMessage = null;
        this.error = error;
    }

    public IError getError() {
        return this.error;
    }

    public String getExtMessage() {
        return this.extMessage;
    }

    public void setExtMessage(String extMessage) {
        this.extMessage = extMessage;
    }
    @Override
    public String toString() {
        return super.toString() + ",ns:" + this.error.getNs() + ",code : " + this.error.getCode() + ", msg : " + this.error.getMsg() + ", ExtMessage : " + this.extMessage;
    }
}
