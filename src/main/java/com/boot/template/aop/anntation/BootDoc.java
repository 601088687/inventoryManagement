package com.boot.template.aop.anntation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @author yishuai
 * @date 2022-06-28 15:57
 **/
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.CONSTRUCTOR, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface BootDoc {
}
