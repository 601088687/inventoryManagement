package com.boot.template.common.exception.system;


import com.boot.template.common.constant.error.ErrorSystemCode;
import com.boot.template.common.exception.CommonException;

/**
 *  用户异常主类
 */
public  class SystemException extends CommonException {

	private static final long serialVersionUID = 1L;

	public SystemException() {
    }

    public SystemException(String message) {
        super(message);
    }

    public SystemException(String message, Throwable cause) {
        super(message, cause);
    }

    public SystemException(Throwable cause) {
        super(cause);
    }

    public SystemException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }


    public String getErrorCode() {
        return ErrorSystemCode.SYSTEM_INSIDE.getCode();
    }

    public String getErrorMsg() {
        return ErrorSystemCode.SYSTEM_INSIDE.getMsg();
    }
}
