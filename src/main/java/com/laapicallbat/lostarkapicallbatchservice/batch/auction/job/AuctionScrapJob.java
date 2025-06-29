package com.laapicallbat.lostarkapicallbatchservice.batch.auction.job;

import com.laapicallbat.lostarkapicallbatchservice.batch.auction.step.GemAuctionScrapStep;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Log4j2
@Configuration
@RequiredArgsConstructor
public class AuctionScrapJob {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;
    private final GemAuctionScrapStep gemAuctionScrapStep;

    @Bean
    public Job gemAuctionScrapJob() {
        return new JobBuilder("gemAuctionScrapJob", jobRepository)
                .start(gemDataScrapStep())
                .build();
    }

    @Bean
    public Step gemDataScrapStep() {
        return new StepBuilder("gemDataScrapStep", jobRepository)
                .tasklet(gemAuctionScrapStep, transactionManager)
                .build();
    }
}
