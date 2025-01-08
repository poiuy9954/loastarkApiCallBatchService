package com.laapicallbat.lostarkapicallbatchservice.aa.config;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties(prefix = "searchdata")
@Getter
@Setter
@ToString
public class SearchDataConfig {
    private List<String> gem;

}
