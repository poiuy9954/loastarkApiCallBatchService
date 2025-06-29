package com.laapicallbat.lostarkapicallbatchservice.batch.auction.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Log4j2
@RestController
@RequestMapping("/api/batch")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class BatchController {

    private final JobLauncher jobLauncher;
    
    @Qualifier("gemAuctionScrapJob")
    private final Job gemAuctionScrapJob;

    /**
     * 배치 상태 확인 (헬스체크용) - GET 방식
     */
    @GetMapping("/test")
    public ResponseEntity<Map<String, Object>> batchTestGet() {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "healthy");
        response.put("timestamp", LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        response.put("service", "Lost Ark Auction Batch Service");
        response.put("message", "GET Test endpoint working - Security Disabled");
        
        return ResponseEntity.ok(response);
    }

    /**
     * 보석 경매장 데이터 스크래핑 배치 수동 실행
     */
    @PostMapping("/gem-auction-scrap")
    public ResponseEntity<Map<String, Object>> runGemAuctionScrapBatch() {
        Map<String, Object> response = new HashMap<>();
        
        try {
            log.info("수동 보석 경매장 데이터 스크래핑 배치 실행 요청: {}", LocalDateTime.now());
            
            JobParameters jobParameters = new JobParametersBuilder()
                    .addLocalDateTime("requestTime", LocalDateTime.now())
                    .addString("trigger", "manual")
                    .toJobParameters();
            
            var jobExecution = jobLauncher.run(gemAuctionScrapJob, jobParameters);
            
            response.put("success", true);
            response.put("message", "배치 실행 성공");
            response.put("jobExecutionId", jobExecution.getId());
            response.put("status", jobExecution.getStatus().toString());
            response.put("startTime", jobExecution.getStartTime() != null ? 
                    jobExecution.getStartTime().toString() : null);
            
            log.info("수동 보석 경매장 데이터 스크래핑 배치 실행 완료: {}", LocalDateTime.now());
            
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            log.error("수동 배치 실행 중 오류 발생: {}", e.getMessage(), e);
            
            response.put("success", false);
            response.put("message", "배치 실행 실패: " + e.getMessage());
            response.put("error", e.getClass().getSimpleName());
            
            return ResponseEntity.internalServerError().body(response);
        }
    }

    /**
     * 배치 상태 확인 (헬스체크용)
     */
    @PostMapping("/health")
    public ResponseEntity<Map<String, Object>> batchHealthCheck() {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "healthy");
        response.put("timestamp", LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        response.put("service", "Lost Ark Auction Batch Service");
        
        return ResponseEntity.ok(response);
    }
}
