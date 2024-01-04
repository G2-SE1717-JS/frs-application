package com.frs.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@ComponentScan(basePackages = {"com.frs.core", "com.frs.application"})
@EnableAsync
@EnableTransactionManagement
@EnableCaching
@EnableJpaAuditing
public class FrsApplication {

    public static void main(String[] args) {
        SpringApplication.run(FrsApplication.class, args);
    }

}
