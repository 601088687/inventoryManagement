/*
package com.boot.template.config.swagger;

import com.boot.template.aop.anntation.BootDoc;
import com.boot.template.common.constant.SystemConstants;
import com.google.common.base.Predicates;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.request.async.DeferredResult;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Collections;

*/
/**
 * @Author yishuai
 * @Date 2022-06-28 15:54
 * /testcenter/swagger-ui.html#/
 *//*

@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket createRestApi(@Value("${swagger.enable}") Boolean enable) {
        return new Docket(DocumentationType.SWAGGER_2)
                .genericModelSubstitutes(DeferredResult.class)
                .globalOperationParameters(Collections.singletonList(
                                new ParameterBuilder()
                                        .name(SystemConstants.ORG_CODE_KEY)
                                        .description("商家code")
                                        .modelRef(new ModelRef("string"))
                                        .parameterType("header")
                                        .required(true)
                                        .build()
                        ))
                .apiInfo(productApiInfo())
                .select()
                .apis(Predicates.or(RequestHandlerSelectors.withMethodAnnotation(BootDoc.class),
                        RequestHandlerSelectors.withClassAnnotation(BootDoc.class)))
                .paths(PathSelectors.any())
                .build()
                .enable(enable);
    }

    private ApiInfo productApiInfo() {
        return
                new ApiInfo(
                        "对外接口",
                        "对外接口",
                        "1.0.0",
                        "API TERMS URL",
                        new Contact("", "http://127.0.0.1:8086/bootTemplate/swagger-ui.html#/", ""),
                        "© 2022",
                        "license url",
                        new ArrayList<>());
    }
}
*/
