package com.laapicallbat.lostarkapicallbatchservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@SpringBootApplication
public class LostarkApiCallBatchServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(LostarkApiCallBatchServiceApplication.class, args);
    }

}
