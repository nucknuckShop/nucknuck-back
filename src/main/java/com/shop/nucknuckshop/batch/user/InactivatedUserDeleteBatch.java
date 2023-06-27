package com.shop.nucknuckshop.batch.user;

import com.shop.nucknuckshop.user.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManagerFactory;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class InactivatedUserDeleteBatch {

    private final JobBuilderFactory jobBuilderFactory;
    private final UserJobListener userJobListener;
    private final StepBuilderFactory stepBuilderFactory;
    private final EntityManagerFactory entityManagerFactory;

    private static final int CHUNK_SIZE = 10;

    @Bean
    public Job jpaPagingItemReaderJob() {
        return jobBuilderFactory.get("jpaPagingItemReaderJob")
                .listener(userJobListener)
                .start(jpaPagingItemReaderStep())
                .build();
    }

    @Bean
    public Step jpaPagingItemReaderStep() {
        return stepBuilderFactory.get("jpaPagingItemReaderStep")
                .<User, User>chunk(CHUNK_SIZE)
                .reader(jpaPagingItemReader())
                .writer(jpaPagingItemWriter())
                .build();
    }

    @Bean
    public JpaPagingItemReader<User> jpaPagingItemReader() {
        return new JpaPagingItemReaderBuilder<User>()
                .name("jpaPagingInactivatedUserDeleteItemReader")
                .entityManagerFactory(entityManagerFactory)
                .pageSize(CHUNK_SIZE)
                .queryString("SELECT email FROM nns_user WHERE user_status = INACTIVE") // AND INACTIVE_DATE < DATE_FORMAT(now(), %Y-%m-%d)
                .build();
    }

    private ItemWriter<User> jpaPagingItemWriter() {
        return list -> {
            for (User user: list) {
                log.info("Deleted user = {}", user);
            }
        };
    }
}
