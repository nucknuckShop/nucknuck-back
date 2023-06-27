package com.shop.nucknuckshop.batch.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@Slf4j
public class UserJobListener implements JobExecutionListener {

    @Override
    public void beforeJob(JobExecution jobExecution) {
       log.info("user job started .....");
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        long time = getRequireEndTime(jobExecution) - getRequireStartTime(jobExecution);
        log.info("user job total time : {}", time);
    }

    private Long getRequireEndTime(JobExecution jobExecution){
        return Objects.requireNonNull(jobExecution.getEndTime()).getTime();
    }

    private Long getRequireStartTime(JobExecution jobExecution){
        return Objects.requireNonNull(jobExecution.getStartTime()).getTime();
    }
}
