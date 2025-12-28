package com.boot.template.common.exception.system;


import com.boot.template.common.constant.error.ErrorSystemCode;
import com.boot.template.common.exception.ErrorCodeCapable;

/**
 *  系统内部错误
 */
public class SystemInsideException extends SystemException implements ErrorCodeCapable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SystemInsideException() {
    }

    public SystemInsideException(String message) {
        super(message);
    }

    public SystemInsideException(String message, Throwable cause) {
        super(message, cause);
    }

    public SystemInsideException(Throwable cause) {
        super(cause);
    }

    public SystemInsideException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public String getErrorCode() {
        return ErrorSystemCode.SYSTEM_INSIDE.getCode();
    }

    @Override
    public String getErrorMsg() {
        return ErrorSystemCode.SYSTEM_INSIDE.getMsg();
    }
}
