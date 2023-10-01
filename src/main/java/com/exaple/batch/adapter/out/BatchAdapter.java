package com.exaple.batch.adapter.out;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * Project : batch
 * Created by OKESTRO
 * Developer : th.eom
 * Date Time : 2023/10/01 4:33 PM
 * Summary :
 **/

@Service
@RequiredArgsConstructor
@Slf4j
public class BatchAdapter {

    private final JobLauncher jobLauncher;

    private final Job job;

    @Scheduled(fixedRate = 5000)
    public void batchRun() {
        try {
            JobParameters jobParameters = new JobParametersBuilder()
                    .addLong("time", System.currentTimeMillis())
                    .toJobParameters();

            JobExecution jobExecution = jobLauncher.run(job, jobParameters);
        } catch (JobExecutionAlreadyRunningException e) {
            //TODO
            throw new RuntimeException(e);
        } catch (JobRestartException e) {
            //TODO
            throw new RuntimeException(e);
        } catch (JobInstanceAlreadyCompleteException e) {
            //TODO
            throw new RuntimeException(e);
        } catch (JobParametersInvalidException e) {
            //TODO
            throw new RuntimeException(e);
        }
    }

}
