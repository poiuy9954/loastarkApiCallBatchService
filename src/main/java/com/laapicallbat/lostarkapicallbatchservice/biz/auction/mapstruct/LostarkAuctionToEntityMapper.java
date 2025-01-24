package com.laapicallbat.lostarkapicallbatchservice.biz.auction.mapstruct;

import com.google.gson.Gson;
import com.laapicallbat.lostarkapicallbatchservice.biz.auction.dto.LostarkAuctionItemsResponseDTO;
import com.laapicallbat.lostarkapicallbatchservice.biz.auction.entity.ItemExchangeEntity;
import org.mapstruct.Mapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface LostarkAuctionToEntityMapper {

    default List<ItemExchangeEntity> toEntity(LostarkAuctionItemsResponseDTO dto,int itemCode) {
        if (dto == null) return new ArrayList<>();
        if (dto.getItems() == null) return new ArrayList<>();

        List<ItemExchangeEntity> entities = new ArrayList<>();

        dto.getItems().forEach(
                item -> {
                    ItemExchangeEntity itemEntity = ItemExchangeEntity.builder()
                            .itemName(item.getName())
                            .itemCode(itemCode)
                            .itemEndDttm(item.getAuctionInfo().getEndDate())
                            .itemOption(new Gson().toJson(item.getOptions()))
                            .itemPrice(item.getAuctionInfo().getBuyPrice())
                            .itemBidPrice(item.getAuctionInfo().getBidPrice())
                            .build();
                    entities.add(itemEntity);
                }
        );
        return entities;
    }

}
