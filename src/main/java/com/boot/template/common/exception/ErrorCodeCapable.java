package com.boot.template.common.exception;

/**
 * 异常错误码抽象类
 */
public interface ErrorCodeCapable {
    public String getErrorCode();

    public String getErrorMsg();
}
