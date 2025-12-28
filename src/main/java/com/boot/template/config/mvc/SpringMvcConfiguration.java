/*
package com.boot.template.config.mvc;

import com.boot.template.config.interceptor.RequestInterceptor;
//import com.boot.template.config.swagger.SwaggerConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.*;

*/
/**
 * @author yishuai
 * @date 2022-06-27 16:46
 **//*

@Configuration
@EnableWebMvc
//@Import(SwaggerConfig.class)
public class SpringMvcConfiguration implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new RequestInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("doc.html", "/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**");
    }
}
*/
