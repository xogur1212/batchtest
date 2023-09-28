package com.exaple.batch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    /**
     * job -> stab 잡안에 하나하나 순서에 대한
     */
    @Bean
    public Job firstJob(JobRepository jobRepository, Step step) {

        return new JobBuilder("firstJob", jobRepository)
                .start(step)
                .build();
    }

    @Bean
    public Step firstStep(JobRepository jobRepository, Tasklet tasklet, PlatformTransactionManager platformTransactionManager) {

        return new StepBuilder("firstStep", jobRepository)
                .tasklet(tasklet, platformTransactionManager)
                .build();
    }

    /**
     * - Tasklet 은 하나의 메소드로 구성되어 있는 인터페이스.
     *
     * - Step 안에서 수행될 기능들을 명시.
     *
     * - 실패를 알리기위해 예외를 반환하거나 Throw 할 때까지 execute 를 반복적으로 호출.
     */
    @Bean
    public Tasklet firstTasklet() {
        return ((contribution, chunkContext) ->{
            System.out.println("test");
            return RepeatStatus.FINISHED;
        });

    }

}

