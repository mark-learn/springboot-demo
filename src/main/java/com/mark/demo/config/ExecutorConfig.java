package com.mark.demo.config;

import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

@Configuration
@Data
public class ExecutorConfig {

    @Bean("myExecutor")
    public ThreadPoolTaskExecutor myExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        //核心线程
        executor.setCorePoolSize(4);
        //最大线程
        executor.setMaxPoolSize(10);
        //队列容量
        executor.setQueueCapacity(1000);
        //保持时间
        executor.setKeepAliveSeconds(60);
        //名称前缀
        executor.setThreadNamePrefix("myExecutor-");
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
        executor.initialize();
        return executor;
    }
}