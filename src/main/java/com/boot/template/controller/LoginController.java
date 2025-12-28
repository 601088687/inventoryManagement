package com.boot.template.controller;

import com.boot.template.common.constant.error.StatusCode;
import com.boot.template.common.result.IResultable;
import com.boot.template.common.result.ResultWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostMapping("/register")
    @ResponseBody
    public IResultable register(String username, String password) {
        // 加密密码再存库
        String encodedPassword = passwordEncoder.encode(password);
        jdbcTemplate.update("INSERT INTO user_info(user_code,user_name, user_password) VALUES('54867612',?, ?)",
                username, encodedPassword);
        return ResultWrapper.wrapObject("注册成功", StatusCode.SUCC.getCode(), StatusCode.SUCC.getMsg());
    }

    @GetMapping("/login")
    public String loginPage(@RequestParam(value = "logout", required = false) String logout, Model model) {
        if (logout != null) {
            model.addAttribute("msg", "您已成功退出系统");
        }
        return "login"; // 返回登录页面模板
    }

    @GetMapping("/getPassword")
    @ResponseBody
    public IResultable getPassword(@RequestParam(value = "pwd")String pwd){
        return ResultWrapper.wrapObject(passwordEncoder.encode(pwd), StatusCode.SUCC.getCode(), StatusCode.SUCC.getMsg());
    }

}