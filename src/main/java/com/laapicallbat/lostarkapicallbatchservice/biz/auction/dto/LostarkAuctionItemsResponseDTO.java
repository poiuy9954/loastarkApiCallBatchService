package com.laapicallbat.lostarkapicallbatchservice.biz.auction.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.laapicallbat.lostarkapicallbatchservice.aa.serializer.MyLocalDateTimeDeserializer;
import com.laapicallbat.lostarkapicallbatchservice.aa.serializer.MyLocalDateTimeSerializer;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class LostarkAuctionItemsResponseDTO {

    private int pageNo;
    private int pageSize;
    private int totalCount;
    private List<Item> items;

    @Builder
    public LostarkAuctionItemsResponseDTO(int pageNo, int pageSize, int totalCount, List<Item> items) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.totalCount = totalCount;
        this.items = items;
    }

    @Getter
    @Setter
    @ToString
    @NoArgsConstructor
    public static class Item{
        private String name;
        private String grade;
        private int tier;
        private int level;
        private String icon;
        @JsonInclude(JsonInclude.Include.NON_DEFAULT)
        private int gradeQuality;
        private AuctionInfo auctionInfo;
        private List<Option> options;

        @Builder
        public Item(String name, String grade, int tier, int level, String icon, int gradeQuality, AuctionInfo auctionInfo, List<Option> options) {
            this.name = name;
            this.grade = grade;
            this.tier = tier;
            this.level = level;
            this.icon = icon;
            this.gradeQuality = gradeQuality;
            this.auctionInfo = auctionInfo;
            this.options = options;
        }
    }

    @Setter
    @ToString
    @Getter
    @NoArgsConstructor
    public static class AuctionInfo{
        private int startPrice;
        private int buyPrice;
        private int bidPrice;

        @JsonDeserialize(using = MyLocalDateTimeDeserializer.class)
        @JsonSerialize(using = MyLocalDateTimeSerializer.class)
        private LocalDateTime endDate;
        private int bidCount;
        private int bidStartPrice;
        private boolean isCompetitive;
        private int tradeAllowCount;

        @Builder
        public AuctionInfo(int startPrice, int buyPrice, int bidPrice, LocalDateTime endDate, int bidCount, int bidStartPrice, boolean isCompetitive, int tradeAllowCount) {
            this.startPrice = startPrice;
            this.buyPrice = buyPrice;
            this.bidPrice = bidPrice;
            this.endDate = endDate;
            this.bidCount = bidCount;
            this.bidStartPrice = bidStartPrice;
            this.isCompetitive = isCompetitive;
            this.tradeAllowCount = tradeAllowCount;
        }
    }

    @Setter
    @ToString
    @Getter
    @NoArgsConstructor
    public static class Option {
        private String type;
        private String optionName;
        private String optionNameTripod;
        private double value;
        private boolean isPenalty;
        private String className;
        private boolean isValuePercentage;

        @Builder
        public Option(String type, String optionName, String optionNameTripod, double value, boolean isPenalty, String className, boolean isValuePercentage) {
            this.type = type;
            this.optionName = optionName;
            this.optionNameTripod = optionNameTripod;
            this.value = value;
            this.isPenalty = isPenalty;
            this.className = className;
            this.isValuePercentage = isValuePercentage;
        }
    }

}
