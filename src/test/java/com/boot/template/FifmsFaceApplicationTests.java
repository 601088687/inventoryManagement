package com.boot.template;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class FifmsFaceApplicationTests {


    @Autowired
    private PasswordEncoder passwordEncoder;
    @Test
    public void getPwd(){
        String encode = passwordEncoder.encode("123");
        System.out.println(encode);
    }
}
