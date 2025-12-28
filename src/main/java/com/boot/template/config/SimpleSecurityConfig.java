package com.boot.template.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SimpleSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/login", "/register").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .defaultSuccessUrl("/index.html")
                .and()
                /* --- 退出登录配置开始 --- */
                .logout()
                .logoutUrl("/logout")               // 处理退出请求的 URL（默认就是 /logout）
                .logoutSuccessUrl("/login?logout")  // 退出成功后跳转的页面
                .invalidateHttpSession(true)        // 使当前 Session 失效
                .deleteCookies("JSESSIONID")        // 删除 Cookie 中的 Session ID
                .clearAuthentication(true)          // 清除认证信息
        /* --- 退出登录配置结束 --- */
        ;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // 必须使用强哈希加密
    }
}