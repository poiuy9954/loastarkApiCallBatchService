package com.laapicallbat.lostarkapicallbatchservice.aa.config;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.laapicallbat.lostarkapicallbatchservice.aa.serializer.LocalDateTimeAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
public class GsonConfig {


    @Bean
    public Gson gson() {
        return new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .registerTypeAdapter(LocalDateTime.class,new LocalDateTimeAdapter()).create();
    }
}
