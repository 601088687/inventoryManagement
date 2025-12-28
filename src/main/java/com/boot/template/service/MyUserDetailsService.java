package com.boot.template.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private JdbcTemplate jdbcTemplate; // 最简单直接用 JdbcTemplate 查询

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String sql = "SELECT user_name,user_password, role FROM user_info WHERE user_name = ?";
        
        return jdbcTemplate.queryForObject(sql, new Object[]{username}, (rs, rowNum) -> 
            User.withUsername(rs.getString("user_name"))
                .password(rs.getString("user_password"))
                .roles(rs.getString("role"))
                .build()
        );
    }
}