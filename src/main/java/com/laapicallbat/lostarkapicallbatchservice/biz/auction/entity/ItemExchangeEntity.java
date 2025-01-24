package com.laapicallbat.lostarkapicallbatchservice.biz.auction.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
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

    @Builder
    public ItemExchangeEntity(int itemCode, String itemName, int itemPrice, int itemBidPrice, LocalDateTime itemEndDttm, String itemOption, LocalDateTime itemScrapStartDttm, LocalDateTime itemScrapUpdateDttm, int itemScrapCount) {
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemBidPrice = itemBidPrice;
        this.itemEndDttm = itemEndDttm;
        this.itemOption = itemOption;
        this.itemScrapStartDttm = itemScrapStartDttm;
        this.itemScrapUpdateDttm = itemScrapUpdateDttm;
        this.itemScrapCount = itemScrapCount;
    }
}

