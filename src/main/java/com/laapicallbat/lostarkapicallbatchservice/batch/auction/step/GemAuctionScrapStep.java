package com.laapicallbat.lostarkapicallbatchservice.batch.auction.step;

import com.laapicallbat.lostarkapicallbatchservice.aa.config.SearchDataConfig;
import com.laapicallbat.lostarkapicallbatchservice.biz.auction.dto.LostarkAuctionItemsRequestDTO;
import com.laapicallbat.lostarkapicallbatchservice.biz.auction.service.AuctionDataCallAndSaveService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Log4j2
@Component
@RequiredArgsConstructor
public class GemAuctionScrapStep implements Tasklet {

    private final AuctionDataCallAndSaveService auctionDataCallAndSaveService;
    private final SearchDataConfig searchDataConfig;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        
        log.info("=== 보석 경매장 데이터 스크래핑 시작 ===");
        log.info("대상 보석 종류: {}", searchDataConfig.getGem().size());
        
        AtomicInteger successCount = new AtomicInteger(0);
        AtomicInteger failCount = new AtomicInteger(0);
        
        List<LostarkAuctionItemsRequestDTO> requestList = auctionDataCallAndSaveService.auctionItemsList();
        
        for (LostarkAuctionItemsRequestDTO request : requestList) {
            try {
                log.info("처리 중: {} ({}/{})", 
                        request.getItemName(), 
                        successCount.get() + failCount.get() + 1, 
                        requestList.size());
                
                auctionDataCallAndSaveService.callAndSaveOrUpdateItem(request);
                successCount.incrementAndGet();
                
                log.info("성공: {}", request.getItemName());
                
                // API 호출 제한 방지를 위한 딜레이
                Thread.sleep(1000);
                
            } catch (Exception e) {
                failCount.incrementAndGet();
                log.error("실패: {} - 오류: {}", request.getItemName(), e.getMessage());
                
                // 실패해도 다음 아이템 계속 처리
                continue;
            }
        }
        
        log.info("=== 보석 경매장 데이터 스크래핑 완료 ===");
        log.info("총 처리: {}, 성공: {}, 실패: {}", 
                requestList.size(), successCount.get(), failCount.get());
        
        // Step execution context에 결과 저장
        contribution.getStepExecution().getExecutionContext().put("successCount", successCount.get());
        contribution.getStepExecution().getExecutionContext().put("failCount", failCount.get());
        contribution.getStepExecution().getExecutionContext().put("totalCount", requestList.size());
        
        return RepeatStatus.FINISHED;
    }
}
