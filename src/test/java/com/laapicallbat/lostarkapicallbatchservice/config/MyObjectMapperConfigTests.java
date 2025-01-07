package com.laapicallbat.lostarkapicallbatchservice.config;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.laapicallbat.lostarkapicallbatchservice.biz.auction.dto.LostarkAuctionItemsResponseDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@SpringBootTest
@Log4j2
public class MyObjectMapperConfigTests {

    @Autowired
    @Qualifier("MyObjectMapper")
    ObjectMapper myObjectMapper;

    @Test
    public void responseDTOParserTest() throws JsonProcessingException {
        String json = "{\n" +
                "    \"PageNo\": 1,\n" +
                "    \"PageSize\": 10,\n" +
                "    \"TotalCount\": 680,\n" +
                "    \"Items\": [\n" +
                "        {\n" +
                "            \"Name\": \"7레벨 멸화의 보석\",\n" +
                "            \"Grade\": \"전설\",\n" +
                "            \"Tier\": 3,\n" +
                "            \"Level\": 0,\n" +
                "            \"Icon\": \"https://cdn-lostark.game.onstove.com/efui_iconatlas/use/use_9_52.png\",\n" +
                "            \"AuctionInfo\": {\n" +
                "                \"StartPrice\": 11000,\n" +
                "                \"BuyPrice\": 11600,\n" +
                "                \"BidPrice\": 0,\n" +
                "                \"EndDate\": \"2025-01-08T03:15:08.297\",\n" +
                "                \"BidCount\": 0,\n" +
                "                \"BidStartPrice\": 11000,\n" +
                "                \"IsCompetitive\": false,\n" +
                "                \"TradeAllowCount\": 0\n" +
                "            },\n" +
                "            \"Options\": [\n" +
                "                {\n" +
                "                    \"Type\": \"GEM_SKILL_DAMAGE\",\n" +
                "                    \"OptionName\": \"여우비 스킬\",\n" +
                "                    \"OptionNameTripod\": \"\",\n" +
                "                    \"Value\": 21.0,\n" +
                "                    \"IsPenalty\": false,\n" +
                "                    \"ClassName\": \"기상술사\",\n" +
                "                    \"IsValuePercentage\": true\n" +
                "                }\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"Name\": \"7레벨 멸화의 보석\",\n" +
                "            \"Grade\": \"전설\",\n" +
                "            \"Tier\": 3,\n" +
                "            \"Level\": 0,\n" +
                "            \"Icon\": \"https://cdn-lostark.game.onstove.com/efui_iconatlas/use/use_9_52.png\",\n" +
                "            \"AuctionInfo\": {\n" +
                "                \"StartPrice\": 11000,\n" +
                "                \"BidPrice\": 0,\n" +
                "                \"EndDate\": \"2025-01-08T03:00:09.363\",\n" +
                "                \"BidCount\": 0,\n" +
                "                \"BidStartPrice\": 11000,\n" +
                "                \"IsCompetitive\": false,\n" +
                "                \"TradeAllowCount\": 0\n" +
                "            },\n" +
                "            \"Options\": [\n" +
                "                {\n" +
                "                    \"Type\": \"GEM_SKILL_DAMAGE\",\n" +
                "                    \"OptionName\": \"필법 : 흩뿌리기\",\n" +
                "                    \"OptionNameTripod\": \"\",\n" +
                "                    \"Value\": 21.0,\n" +
                "                    \"IsPenalty\": false,\n" +
                "                    \"ClassName\": \"도화가\",\n" +
                "                    \"IsValuePercentage\": true\n" +
                "                }\n" +
                "            ]\n" +
                "        }\n" +
                "    ]\n" +
                "}";


        LostarkAuctionItemsResponseDTO lostarkAuctionItemsResponseDTO = myObjectMapper.readValue(json, LostarkAuctionItemsResponseDTO.class);
        Assertions.assertEquals(LocalDateTime.parse("2025-01-08T03:00:09.363", DateTimeFormatter.ISO_DATE_TIME),lostarkAuctionItemsResponseDTO.getItems().get(1).getAuctionInfo().getEndDate());
        Assertions.assertEquals("도화가",lostarkAuctionItemsResponseDTO.getItems().get(1).getOptions().get(0).getClassName());
    }
}
