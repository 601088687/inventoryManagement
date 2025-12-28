package com.boot.template.common.exception.system;


import com.boot.template.common.constant.error.ErrorSystemCode;
import com.boot.template.common.exception.ErrorCodeCapable;

/**
 *  参数错误
 */
public class SystemParameterException extends SystemException implements ErrorCodeCapable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SystemParameterException() {
    }

    public SystemParameterException(String message) {
        super(message);
    }

    public SystemParameterException(String message, Throwable cause) {
        super(message, cause);
    }

    public SystemParameterException(Throwable cause) {
        super(cause);
    }

    public SystemParameterException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public String getErrorCode() {
        return ErrorSystemCode.SYSTEM_PARAMETER.getCode();
    }

    @Override
    public String getErrorMsg() {
        return ErrorSystemCode.SYSTEM_PARAMETER.getMsg();
    }
}
