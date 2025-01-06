package com.laapicallbat.lostarkapicallbatchservice.biz.auction.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Data
public class LostarkAuctionItemsRequestDTO {

    private int itemLevelMin;
    private int itemLevelMax;
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private int itemGradeQuality;
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private int itemUpgradeLevel;
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private int itemTradeAllowCount;
    private Sort sort;
    private int categoryCode;
    private String characterClass;
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private int itemTier;
    private String itemGrade;
    private String itemName;
    private int pageNo;
    private SortCondition sortCondition;
    private List<Option> SkillOptions;
    private List<Option> EtcOptions;

    @Builder
    public LostarkAuctionItemsRequestDTO(int itemLevelMin, int itemLevelMax, int itemGradeQuality, int itemUpgradeLevel, int itemTradeAllowCount, Sort sort, int categoryCode, String characterClass, int itemTier, String itemGrade, String itemName, int pageNo, SortCondition sortCondition, List<Option> skillOptions, List<Option> etcOptions) {
        this.itemLevelMin = itemLevelMin;
        this.itemLevelMax = itemLevelMax;
        this.itemGradeQuality = itemGradeQuality;
        this.itemUpgradeLevel = itemUpgradeLevel;
        this.itemTradeAllowCount = itemTradeAllowCount;
        this.sort = sort;
        this.categoryCode = categoryCode;
        this.characterClass = characterClass;
        this.itemTier = itemTier;
        this.itemGrade = itemGrade;
        this.itemName = itemName;
        this.pageNo = pageNo;
        this.sortCondition = sortCondition;
        SkillOptions = skillOptions;
        EtcOptions = etcOptions;
    }


    @NoArgsConstructor
    @Getter
    @ToString
    public static class Option {
        @Builder
        public Option(int firstOption, int secondOption, int minValue, int maxValue) {
            FirstOption = firstOption;
            SecondOption = secondOption;
            MinValue = minValue;
            MaxValue = maxValue;
        }

        @JsonInclude(JsonInclude.Include.NON_DEFAULT)
        private int FirstOption;
        @JsonInclude(JsonInclude.Include.NON_DEFAULT)
        private int SecondOption;
        @JsonInclude(JsonInclude.Include.NON_DEFAULT)
        private int MinValue;
        @JsonInclude(JsonInclude.Include.NON_DEFAULT)
        private int MaxValue;
    }

    @AllArgsConstructor
    @Getter
    public enum Sort {
        BIDSTART_PRICE("BIDSTART_PRICE"), BUY_PRICE("BUY_PRICE"), EXPIREDATE("EXPIREDATE"),
        ITEM_GRADE("ITEM_GRADE"), ITEM_LEVEL("ITEM_LEVEL"), ITEM_QUALITY("ITEM_QUALITY");
        private final String value;
    }
    @AllArgsConstructor
    @Getter
    public enum SortCondition{
        ASC("ASC"), DESC("DESC");
        private final String value;
    }
}
