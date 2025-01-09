package com.laapicallbat.lostarkapicallbatchservice.aa.config;


import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
//@ConfigurationProperties(prefix = "jwt")
public class JwtConfig {

    @Value("${jwt.token}")
    private String token;
    @Value("${jwt.prefix}")
    private String prefix;
}


