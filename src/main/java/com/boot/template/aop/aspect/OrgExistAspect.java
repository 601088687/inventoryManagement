/*
package com.boot.template.aop.aspect;

import com.boot.template.common.constant.ErrorMsgConstants;
import com.boot.template.common.constant.SystemConstants;
import com.boot.template.common.exception.system.SystemParameterException;
import com.boot.template.common.thread.ThreadLocalMap;
import com.boot.template.pojo.entity.ProductInventoryEntity;
import com.boot.template.service.OrganizationInfoService;
import com.boot.template.service.ProductInventoryService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Objects;

*/
/**
 * @author apple
 *//*

@Aspect
@Component
@Slf4j
public class OrgExistAspect {

    @Autowired
    private ProductInventoryService productInventoryService;

    */
/**
     * 验签切点(完整的找到设置的文件地址)
     *//*

    @Pointcut("execution(@com.boot.template.aop.anntation.OrgExist * *(..))")
    private void orgExist() {
    }

    */
/**
     * 获取请求数据，并校验签名数据
     *//*

    @Before("orgExist()")
    public void before(JoinPoint point) throws SystemParameterException {
        String orgCode = (String) ThreadLocalMap.get(SystemConstants.ORG_CODE_KEY);
        ProductInventoryEntity organizationInfoEntity = productInventoryService(orgCode);
        if (Objects.isNull(organizationInfoEntity)) {
            String requestURI = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getRequestURI();
            log.warn("OrgExistAspect url=[{}],orgInfo not exist,orgCode={} ", requestURI, orgCode);
            throw new SystemParameterException(ErrorMsgConstants.PARAM_ERROR);
        }
    }
}
*/
