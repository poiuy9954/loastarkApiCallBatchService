package com.laapicallbat.lostarkapicallbatchservice.biz.auction.entity;


import java.time.LocalDateTime;

public class BaseEntity {

    private LocalDateTime SYS_CREATION_DTTM;
    private LocalDateTime SYS_UPDATE_DTTM;
    private String SYS_SERVICE_NAME;

    public BaseEntity() {
        this.SYS_CREATION_DTTM = LocalDateTime.now();
        this.SYS_UPDATE_DTTM = SYS_UPDATE_DTTM;
        this.SYS_SERVICE_NAME = SYS_SERVICE_NAME;
    }
}
