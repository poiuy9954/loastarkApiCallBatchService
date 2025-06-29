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
     * 로컬 환경 - 매 2분마다 보석 경매장 데이터 스크래핑 실행
     * cron: 0 *\/2 * * * * (매 2분마다)
     */
    @Scheduled(cron = "0 */2 * * * *")
    public void runGemAuctionScrapJob() {
        try {
            log.info("=== 스케줄된 보석 경매장 데이터 스크래핑 배치 시작: {} ===", LocalDateTime.now());
            
            JobParameters jobParameters = new JobParametersBuilder()
                    .addLocalDateTime("requestTime", LocalDateTime.now())
                    .addString("trigger", "scheduled")
                    .toJobParameters();
            
            var jobExecution = jobLauncher.run(gemAuctionScrapJob, jobParameters);
            
            log.info("=== 스케줄된 보석 경매장 데이터 스크래핑 배치 완료: {} ===", LocalDateTime.now());
            log.info("Job Execution ID: {}, Status: {}", jobExecution.getId(), jobExecution.getStatus());
            
        } catch (Exception e) {
            log.error("스케줄된 배치 실행 중 오류 발생: {}", e.getMessage(), e);
        }
    }

    /**
     * 로컬 테스트용 - 30초마다 실행 (필요시 활성화)
     * 주석 해제하면 30초마다 실행됨
     */
    // @Scheduled(fixedRate = 30000) // 30초마다
    public void testScheduledJob() {
        try {
            log.info("=== 테스트 스케줄 실행: {} ===", LocalDateTime.now());
            
            JobParameters jobParameters = new JobParametersBuilder()
                    .addLocalDateTime("requestTime", LocalDateTime.now())
                    .addString("trigger", "test-scheduled")
                    .toJobParameters();
            
            var jobExecution = jobLauncher.run(gemAuctionScrapJob, jobParameters);
            
            log.info("=== 테스트 스케줄 완료: {}, Status: {} ===", 
                    LocalDateTime.now(), jobExecution.getStatus());
            
        } catch (Exception e) {
            log.error("테스트 스케줄 실행 중 오류 발생: {}", e.getMessage(), e);
        }
    }
}
