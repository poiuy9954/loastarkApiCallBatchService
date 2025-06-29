package com.laapicallbat.lostarkapicallbatchservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@Slf4j
@SpringBootApplication
public class LostarkApiCallBatchServiceApplication {

    public static void main(String[] args) {
        log.info("=== Lostark API Call Batch Service Starting ===");
        SpringApplication.run(LostarkApiCallBatchServiceApplication.class, args);
        log.info("=== Lostark API Call Batch Service Started Successfully ===");
    }

}
