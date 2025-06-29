package com.laapicallbat.lostarkapicallbatchservice.biz.auction.service;

import com.google.gson.Gson;
import com.laapicallbat.lostarkapicallbatchservice.aa.config.SearchDataConfig;
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
    private final SearchDataConfig searchDataConfig;


    public List<LostarkAuctionItemsRequestDTO> auctionItemsList() {
        return searchDataConfig.getGem().stream()
                .map(this::createGemRequest)
                .toList();
    }

    private LostarkAuctionItemsRequestDTO createGemRequest(String gemName) {
        return createGemRequest(gemName, 0);
    }

    public void processAllGemsData() {
        log.info("보석 데이터 스크래핑 시작");
        
        searchDataConfig.getGem().forEach(gemName -> {
            try {
                log.info("보석 데이터 수집 시작: {}", gemName);
                processAllPagesForGem(gemName);
                
                // 각 보석 처리 후 딜레이 (2초)
                Thread.sleep(2000);
                
            } catch (Exception e) {
                log.error("보석 데이터 수집 실패: {} - {}", gemName, e.getMessage());
            }
        });
        
        log.info("보석 데이터 스크래핑 완료");
    }

    private void processAllPagesForGem(String gemName) {
        try {
            // 첫 번째 페이지 요청으로 총 페이지 수 확인
            LostarkAuctionItemsRequestDTO firstRequest = createGemRequest(gemName, 0);
            LostarkAuctionItemsResponseDTO firstResponse = auctionItemsCall(firstRequest);
            
            if (firstResponse == null || firstResponse.getTotalCount() == 0) {
                log.info("보석 {} - 데이터 없음", gemName);
                return;
            }
            
            // 총 페이지 수 계산
            int totalPages = calculateTotalPages(firstResponse.getTotalCount(), firstResponse.getPageSize());
            log.info("보석 {} - 총 {}개 페이지, 총 {}개 아이템", gemName, totalPages, firstResponse.getTotalCount());
            
            // 첫 번째 페이지 데이터 저장
            savePageData(firstResponse, firstRequest.getCategoryCode());
            
            // 나머지 페이지들 처리
            for (int page = 1; page < totalPages; page++) {
                try {
                    log.info("보석 {} - {}/{} 페이지 처리 중", gemName, page + 1, totalPages);
                    
                    LostarkAuctionItemsRequestDTO pageRequest = createGemRequest(gemName, page);
                    LostarkAuctionItemsResponseDTO pageResponse = auctionItemsCall(pageRequest);
                    
                    if (pageResponse != null && pageResponse.getItems() != null && !pageResponse.getItems().isEmpty()) {
                        savePageData(pageResponse, pageRequest.getCategoryCode());
                    }
                    
                    // 페이지 간 딜레이 (1초)
                    Thread.sleep(1000);
                    
                } catch (Exception e) {
                    log.error("보석 {} - {} 페이지 처리 실패: {}", gemName, page + 1, e.getMessage());
                    // 한 페이지 실패해도 다음 페이지 계속 처리
                }
            }
            
            log.info("보석 {} - 모든 페이지 처리 완료", gemName);
            
        } catch (Exception e) {
            log.error("보석 {} - 전체 처리 실패: {}", gemName, e.getMessage());
        }
    }

    private LostarkAuctionItemsRequestDTO createGemRequest(String gemName, int pageNo) {
        return LostarkAuctionItemsRequestDTO.builder()
                .categoryCode(210000)  // 보석 카테고리 코드
                .itemName(gemName)     // 보석 이름
                .pageNo(pageNo)        // 페이지 번호
                .sort(LostarkAuctionItemsRequestDTO.Sort.BUY_PRICE)  // 즉시구매가 정렬
                .sortCondition(LostarkAuctionItemsRequestDTO.SortCondition.ASC)  // 오름차순
                .build();
    }

    private int calculateTotalPages(int totalCount, int pageSize) {
        if (pageSize <= 0) {
            pageSize = 10; // 기본값
        }
        return (int) Math.ceil((double) totalCount / pageSize);
    }

    private void savePageData(LostarkAuctionItemsResponseDTO response, int categoryCode) {
        if (response == null || response.getItems() == null || response.getItems().isEmpty()) {
            return;
        }
        
        List<ItemExchangeEntity> entities = lostarkAuctionToEntityMapper.toEntity(response, categoryCode);
        entities.forEach(this::upsert);
        
        log.debug("페이지 데이터 저장 완료 - {}개 아이템", entities.size());
    }

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
