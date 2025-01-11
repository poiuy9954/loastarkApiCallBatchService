package com.laapicallbat.lostarkapicallbatchservice.biz.auction.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
public class ItemExchangeEntity {

    private int itemCode;
    private String itemName;
    private int itemPrice;
    private int itemBidPrice;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    private LocalDateTime itemEndDttm;
    private String itemOption;
    private LocalDateTime itemScrapStartDttm;
    private LocalDateTime itemScrapUpdateDttm;
    private int itemScrapCount;
    private LocalDateTime sysCreationDttm;
    private LocalDateTime sysUpdateDttm;
    private String sysServiceName;
    private String sysFuncName;
}

