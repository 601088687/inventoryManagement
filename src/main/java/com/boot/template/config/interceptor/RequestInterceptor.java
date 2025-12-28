/*
package com.boot.template.config.interceptor;

import com.boot.template.common.constant.SystemConstants;
import com.boot.template.common.exception.system.SystemParameterException;
import com.boot.template.common.thread.ThreadLocalMap;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

*/
/**
 * @author yishuai
 * @date 2022-06-27 16:39
 * 请求拦截
 * 请求来源- - 等信息
 **//*

public class RequestInterceptor implements HandlerInterceptor {
    Logger log = LoggerFactory.getLogger(RequestInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String orgCode = request.getHeader(SystemConstants.ORG_CODE_KEY);
        if (StringUtils.isBlank(orgCode)) {
            throw new SystemParameterException("请求参数错误");
        }
        ThreadLocalMap.put(SystemConstants.ORG_CODE_KEY, orgCode);
        return true;
    }
}
*/
