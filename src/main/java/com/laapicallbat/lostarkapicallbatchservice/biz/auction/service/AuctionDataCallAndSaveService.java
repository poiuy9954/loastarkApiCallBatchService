package com.laapicallbat.lostarkapicallbatchservice.biz.auction.service;

import com.google.gson.Gson;
import com.laapicallbat.lostarkapicallbatchservice.biz.auction.dto.LostarkAuctionItemsRequestDTO;
import com.laapicallbat.lostarkapicallbatchservice.biz.auction.dto.LostarkAuctionItemsResponseDTO;
import com.laapicallbat.lostarkapicallbatchservice.biz.auction.entity.ItemExchangeEntity;
import com.laapicallbat.lostarkapicallbatchservice.biz.auction.mapper.AuctionMapper;
import com.laapicallbat.lostarkapicallbatchservice.biz.auction.mapstruct.LostarkAuctionToEntityMapper;
import com.laapicallbat.lostarkapicallbatchservice.biz.rest.auction.service.RestAuctionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class AuctionDataCallAndSaveService {
    private final RestAuctionService restAuctionService;
    private final AuctionMapper auctionMapper;

    private final LostarkAuctionToEntityMapper lostarkAuctionToEntityMapper;
    private final Gson gson;
    private final AuctionCRUDService auctionCRUDService;

    public void callAndSaveOrUpdateItem(LostarkAuctionItemsRequestDTO lostarkAuctionItemsRequestDTO){

        LostarkAuctionItemsResponseDTO dto = this.auctionItemsCall(lostarkAuctionItemsRequestDTO);

        List<ItemExchangeEntity> entities = lostarkAuctionToEntityMapper.toEntity(dto, lostarkAuctionItemsRequestDTO.getCategoryCode());

        entities.forEach(this::upsert);
    }

    private LostarkAuctionItemsResponseDTO auctionItemsCall(LostarkAuctionItemsRequestDTO lostarkAuctionItemsRequestDTO){
        LostarkAuctionItemsResponseDTO responseDTO = restAuctionService.postItems(lostarkAuctionItemsRequestDTO);
        return responseDTO;
    }

    private UPSERT auctionItemDataVerify(ItemExchangeEntity entity){
        return auctionCRUDService.auctionItemData(entity) != null ? UPSERT.UPDATE : UPSERT.INSERT;
    }

    private void upsert(ItemExchangeEntity callEntity){
        ItemExchangeEntity data = auctionCRUDService.auctionItemData(callEntity);
        data = (data == null) ? callEntity : data;

        switch (auctionItemDataVerify(data)){
            case INSERT ->insertDataSetAndSave(data);
            case UPDATE ->updateDataSetAndUpdate(data);
        }
    }

    private void updateDataSetAndUpdate(ItemExchangeEntity data) {
        ItemExchangeEntity updateEntity = ItemExchangeEntity.builder()
                .itemCode(data.getItemCode())
                .itemName(data.getItemName())
                .itemEndDttm(data.getItemEndDttm())
                .itemBidPrice(data.getItemBidPrice())
                .itemScrapUpdateDttm(LocalDateTime.now())
                .itemScrapCount(data.getItemScrapCount()+1)
                .build();
        auctionCRUDService.auctionItemUpdate(updateEntity);
    }

    private void insertDataSetAndSave(ItemExchangeEntity data) {
        data.setItemScrapCount(1);
        data.setItemScrapStartDttm(LocalDateTime.now());
        data.setItemScrapUpdateDttm(LocalDateTime.now());
        auctionCRUDService.auctionItemSave(data);
    }

    private enum UPSERT {
        UPDATE,INSERT
    }

}
