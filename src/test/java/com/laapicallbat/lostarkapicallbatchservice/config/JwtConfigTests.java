package com.laapicallbat.lostarkapicallbatchservice.config;

import com.laapicallbat.lostarkapicallbatchservice.aa.config.JwtConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JwtConfigTests {

    @Autowired
    private JwtConfig jwtConfig;

    @Test
    public void jwtTokenTest() {
        Assertions.assertNotNull(jwtConfig.getPrefix());
    }

    @Test
    public void jwtPrefixTest(){
        Assertions.assertEquals("bearer",jwtConfig.getPrefix());
    }
}
