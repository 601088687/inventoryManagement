package com.boot.template.common.exception.system;


import com.boot.template.common.constant.error.ErrorSystemCode;
import com.boot.template.common.exception.ErrorCodeCapable;

/**
 *  操作超时
 */
public class SystemTimeoutException extends SystemException implements ErrorCodeCapable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SystemTimeoutException() {
    }

    public SystemTimeoutException(String message) {
        super(message);
    }

    public SystemTimeoutException(String message, Throwable cause) {
        super(message, cause);
    }

    public SystemTimeoutException(Throwable cause) {
        super(cause);
    }

    public SystemTimeoutException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public String getErrorCode() {
        return ErrorSystemCode.SYSTEM_TIMEOUT.getCode();
    }

    @Override
    public String getErrorMsg() {
        return ErrorSystemCode.SYSTEM_TIMEOUT.getMsg();
    }
}
