package com.laapicallbat.lostarkapicallbatchservice.biz.auction.dto;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Data
public class LostarkAuctionItemsRequestDTO {

    private String itemLevelMin;
    private String itemLevelMax;
    private String itemGradeQuality;
    private String itemUpgradeLevel;
    private String itemTradeAllowCount;
    private Sort sort;
    private String categoryCode;
    private String characterClass;
    private String itemTier;
    private String itemGrade;
    private String itemName;
    private int pageNo;
    private SortCondition sortCondition;
    private List<Option> SkillOptions;
    private List<Option> EtcOptions;

    @Builder
    public LostarkAuctionItemsRequestDTO(String itemLevelMin, String itemLevelMax, String itemGradeQuality, String itemUpgradeLevel, String itemTradeAllowCount, Sort sort, String categoryCode, String characterClass, String itemTier, String itemGrade, String itemName, int pageNo, SortCondition sortCondition, List<Option> skillOptions, List<Option> etcOptions) {
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
    public static class Option {
        @Builder
        public Option(int firstOption, int secondOption, int minValue, int maxValue) {
            FirstOption = firstOption;
            SecondOption = secondOption;
            MinValue = minValue;
            MaxValue = maxValue;
        }

        private int FirstOption;
        private int SecondOption;
        private int MinValue;
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
