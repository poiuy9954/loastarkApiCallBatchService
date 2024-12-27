package com.laapicallbat.lostarkapicallbatchservice.aa.config;

import com.laapicallbat.lostarkapicallbatchservice.aa.customclass.MyRestTemplate;
import com.laapicallbat.lostarkapicallbatchservice.aa.interceptor.RequestJwtTokenAddInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyRestTemplateConfig {

    private final RequestJwtTokenAddInterceptor requestJwtTokenAddInterceptor;
    private final DomainConfig domainConfig;


    public MyRestTemplateConfig(RequestJwtTokenAddInterceptor requestJwtTokenAddInterceptor, DomainConfig domainConfig) {
        this.requestJwtTokenAddInterceptor = requestJwtTokenAddInterceptor;
        this.domainConfig = domainConfig;
    }

    @Bean
    public MyRestTemplate restTemplate(){
        MyRestTemplate restTemplate = new MyRestTemplate(domainConfig);
        restTemplate.getInterceptors().add(requestJwtTokenAddInterceptor);
        return restTemplate;
    }
}
