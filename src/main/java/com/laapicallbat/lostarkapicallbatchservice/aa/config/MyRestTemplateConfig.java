package com.laapicallbat.lostarkapicallbatchservice.aa.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.laapicallbat.lostarkapicallbatchservice.aa.customclass.MyRestTemplate;
import com.laapicallbat.lostarkapicallbatchservice.aa.interceptor.MyRestTemplateLoggingInterceptor;
import com.laapicallbat.lostarkapicallbatchservice.aa.interceptor.RequestJwtTokenAddInterceptor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.util.List;

@Configuration
public class MyRestTemplateConfig {

    private final RequestJwtTokenAddInterceptor requestJwtTokenAddInterceptor;
    private final MyRestTemplateLoggingInterceptor myRestTemplateLoggingInterceptor;
    private final DomainConfig domainConfig;
    private final ObjectMapper myObjectMapper;
//    private final Gson gson;


    public MyRestTemplateConfig(RequestJwtTokenAddInterceptor requestJwtTokenAddInterceptor, MyRestTemplateLoggingInterceptor myRestTemplateLoggingInterceptor, DomainConfig domainConfig, Gson gson, @Qualifier("MyObjectMapper") ObjectMapper objectMapper) {
        this.requestJwtTokenAddInterceptor = requestJwtTokenAddInterceptor;
        this.myRestTemplateLoggingInterceptor = myRestTemplateLoggingInterceptor;
        this.domainConfig = domainConfig;
        this.myObjectMapper = objectMapper;
    }

    @Bean
    public MyRestTemplate restTemplate(){
        MyRestTemplate restTemplate = new MyRestTemplate(domainConfig);
        restTemplate.getInterceptors().add(0,requestJwtTokenAddInterceptor);
        restTemplate.getInterceptors().add(1, myRestTemplateLoggingInterceptor);

        List<HttpMessageConverter<?>> messageConverters = restTemplate.getMessageConverters();
        for (HttpMessageConverter<?> messageConverter : messageConverters) {
            if (messageConverter instanceof MappingJackson2HttpMessageConverter) {
                ((MappingJackson2HttpMessageConverter) messageConverter).setObjectMapper(myObjectMapper);
            }
        }

        return restTemplate;
    }
}
