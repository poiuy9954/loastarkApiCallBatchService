package com.laapicallbat.lostarkapicallbatchservice.biz.auction.service;

import com.laapicallbat.lostarkapicallbatchservice.biz.auction.entity.ItemExchangeEntity;
import com.laapicallbat.lostarkapicallbatchservice.biz.auction.mapper.AuctionMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Log4j2
@Transactional
@RequiredArgsConstructor
public class AuctionCRUDService {

    private final AuctionMapper auctionMapper;


    public int auctionItemSave(ItemExchangeEntity entity){
        return auctionMapper.insert(entity);
    }
    public int auctionItemUpdate(ItemExchangeEntity entity){
        return auctionMapper.update(entity);
    }

    public ItemExchangeEntity auctionItemData(ItemExchangeEntity entity){
        return auctionMapper.selectById(entity.getItemCode(),entity.getItemName(),entity.getItemEndDttm());
    }
}
