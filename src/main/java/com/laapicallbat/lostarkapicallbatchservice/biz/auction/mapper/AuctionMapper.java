package com.laapicallbat.lostarkapicallbatchservice.biz.auction.mapper;

import com.laapicallbat.lostarkapicallbatchservice.biz.auction.entity.ItemExchangeEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface AuctionMapper {

    @Select("SELECT NOW()")
    public LocalDateTime now();

    @Select("SELECT * FROM lostarkDB.TB_ITEM_EXCHANGE")
    public List<ItemExchangeEntity> selectAll();

    @Select("SELECT * FROM lostarkDB.TB_ITEM_EXCHANGE where (ITEM_CODE,ITEM_NAME,ITEM_END_DTTM) = (#{itemCode},#{itemName},#{itemEndDttm})")
    public ItemExchangeEntity selectById(int itemCode, String itemName, LocalDateTime itemEndDttm);

    @Insert("""
            insert into TB_ITEM_EXCHANGE
            (item_code, item_name, item_price, item_bid_price, item_end_dttm, item_option, item_scrap_start_dttm, item_scrap_update_dttm, item_scrap_count)
            value
            (#{itemCode},#{itemName},#{itemPrice},#{itemBidPrice},#{itemEndDttm},#{itemOption},#{itemScrapStartDttm},#{itemScrapUpdateDttm},#{itemScrapCount});""")
    public int insert(ItemExchangeEntity entity);

    public int update(ItemExchangeEntity entity);



}
