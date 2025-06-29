package com.laapicallbat.lostarkapicallbatchservice.aa.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;

@Log4j2
@Configuration
@EnableScheduling
@RequiredArgsConstructor
public class BatchScheduleConfig {

    private final JobLauncher jobLauncher;
    
    @Qualifier("gemAuctionScrapJob")
    private final Job gemAuctionScrapJob;

    /**
     * 매 30분마다 보석 경매장 데이터 스크래핑 실행
     * cron: 0 *\/30 * * * * (매 30분마다)
     */
    @Scheduled(cron = "0 */30 * * * *")
    public void runGemAuctionScrapJob() {
        try {
            log.info("스케줄된 보석 경매장 데이터 스크래핑 배치 시작: {}", LocalDateTime.now());
            
            JobParameters jobParameters = new JobParametersBuilder()
                    .addLocalDateTime("requestTime", LocalDateTime.now())
                    .toJobParameters();
            
            jobLauncher.run(gemAuctionScrapJob, jobParameters);
            
            log.info("스케줄된 보석 경매장 데이터 스크래핑 배치 완료: {}", LocalDateTime.now());
            
        } catch (Exception e) {
            log.error("스케줄된 배치 실행 중 오류 발생: {}", e.getMessage(), e);
        }
    }

    /**
     * 매일 자정에 일일 데이터 정리 작업 (선택사항)
     */
    @Scheduled(cron = "0 0 0 * * *")
    public void dailyDataCleanup() {
        log.info("일일 데이터 정리 작업 시작: {}", LocalDateTime.now());
        // TODO: 오래된 데이터 정리 로직 구현
        log.info("일일 데이터 정리 작업 완료: {}", LocalDateTime.now());
    }
}
