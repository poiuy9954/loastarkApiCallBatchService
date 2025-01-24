package com.laapicallbat.lostarkapicallbatchservice.biz.auction.mapper;

import com.laapicallbat.lostarkapicallbatchservice.biz.auction.entity.ItemExchangeEntity;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@SpringBootTest
@Log4j2
@Transactional
public class AuctionMapperTests {

    @Autowired
    private AuctionMapper auctionMapper;

    @BeforeAll
    public static void beforeAll() {

    }

    @Test
    public void now_Test() {

        LocalDateTime now = LocalDateTime.now();
        log.debug("now :: {} ", now);
    }

    @Test
    public void selectAll() {

        List<ItemExchangeEntity> list = auctionMapper.selectAll();
        log.debug("list :: {}", list);
    }

    @Test
    public void selectById() {

        int itemCode = 1;
        String itemName = "더미아이템";
        LocalDateTime endDTTM = LocalDateTime.parse("2025-01-12T01:04:47.753",DateTimeFormatter.ISO_DATE_TIME);
        ItemExchangeEntity itemExchangeEntity = auctionMapper.selectById(itemCode, itemName, endDTTM);


    }


    @Test
    public void insert() {
        LocalDateTime date = LocalDateTime.now();
        date.format(DateTimeFormatter.ISO_DATE_TIME);
        LocalDateTime dateAddDay = LocalDateTime.now().plusDays(1);
        dateAddDay.format(DateTimeFormatter.ISO_DATE_TIME);

        ItemExchangeEntity itemExchangeEntity = new ItemExchangeEntity();
        itemExchangeEntity.setItemCode(1);
        itemExchangeEntity.setItemName("테스트터미1");
        itemExchangeEntity.setItemPrice(30022);
        itemExchangeEntity.setItemBidPrice(0);
        itemExchangeEntity.setItemEndDttm(dateAddDay);
        itemExchangeEntity.setItemOption(null);
        itemExchangeEntity.setItemScrapStartDttm(date);
        itemExchangeEntity.setItemScrapUpdateDttm(date);
        itemExchangeEntity.setItemScrapCount(1);

        int result = auctionMapper.insert(itemExchangeEntity);
        Assertions.assertEquals(1, result);
    }

    @Test
    public void update() {
        LocalDateTime date = LocalDateTime.now();
        date.format(DateTimeFormatter.ISO_DATE_TIME);
        LocalDateTime endDate = LocalDateTime.parse("2025-01-12T11:23:52.514",DateTimeFormatter.ISO_DATE_TIME);

        ItemExchangeEntity itemExchangeEntity = new ItemExchangeEntity();
        itemExchangeEntity.setItemCode(1);
        itemExchangeEntity.setItemName("테스트터미1");
        itemExchangeEntity.setItemEndDttm(endDate);
        itemExchangeEntity.setItemScrapUpdateDttm(date);

        int result = auctionMapper.update(itemExchangeEntity);

        ItemExchangeEntity itemExchangeEntity2 = new ItemExchangeEntity();
        itemExchangeEntity2.setItemCode(1);
        itemExchangeEntity2.setItemName("테스트터미1");
        itemExchangeEntity2.setItemEndDttm(endDate);
        ItemExchangeEntity print = auctionMapper.selectById(1,"테스트터미1",endDate);

        Assertions.assertEquals(1,result);

    }
}