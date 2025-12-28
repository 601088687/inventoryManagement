package com.boot.template.config.exceptionhandler;

import com.boot.template.common.constant.error.ErrorSystemCode;
import com.boot.template.common.constant.error.StatusCode;
import com.boot.template.common.exception.system.SystemInsideException;
import com.boot.template.common.exception.system.SystemParameterException;
import com.boot.template.common.result.IResultable;
import com.boot.template.common.result.ResultWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.stream.Collectors;


/**
 * @author yishuai
 * @date 2022-06-27 16:35
 * 统一异常处理
 **/
@ControllerAdvice
public class CommonExceptionHandler {

    Logger log = LoggerFactory.getLogger(CommonExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public IResultable exceptionHandler(Exception e) {
        log.error("system Exception!", e);
        return ResultWrapper.wrapObject(null, StatusCode.FAIL.getCode(), ErrorSystemCode.SYSTEM_INSIDE.getMsg(), ErrorSystemCode.SYSTEM_INSIDE.getCode());
    }

    @ExceptionHandler(value = SystemParameterException.class)
    @ResponseBody
    public IResultable parameterExceptionHandler(SystemParameterException e) {
        log.error("request parameter Exception!", e);
        return ResultWrapper.wrapObject(null, StatusCode.FAIL.getCode(), e.getMessage(), e.getErrorCode());
    }

    @ExceptionHandler(value = SystemInsideException.class)
    @ResponseBody
    public IResultable insideExceptionHandler(SystemInsideException e) {
        log.error("system inside Exception!", e);
        return ResultWrapper.wrapObject(null, StatusCode.FAIL.getCode(), e.getMessage(), e.getErrorCode());
    }

    @ExceptionHandler(value = BindException.class)
    @ResponseBody
    public IResultable bindExceptionHandler(BindException e) {
        log.error("request param error!", e);
        List<ObjectError> allErrors = e.getBindingResult().getAllErrors();
        String message = allErrors.stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining(";"));
        return ResultWrapper.wrapObject(null, StatusCode.FAIL.getCode(), message, ErrorSystemCode.SYSTEM_PARAMETER.getCode());
    }

}
