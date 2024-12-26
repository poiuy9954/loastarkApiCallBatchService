package com.laapicallbat.lostarkapicallbatchservice.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "api-domain")
public class DomainConfig {

    private Map<String,Map<String,String>> url;


    @Override
    public String toString() {
        return "DomainConfig{" +
                "url=" + url +
                '}';
    }
}
