package com.mark.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author mark
 * @date 2023年02月07日 14:36:56
 */
@Slf4j
@Component
public class RetryService {

    private static AtomicLong atomicLong = new AtomicLong(0);

    @Retryable(value = RuntimeException.class,maxAttempts = 5,backoff = @Backoff(delay = 1000))
    public String executeRetry() {

        RetryTemplate retryTemplate = RetryTemplate.builder()
                .maxAttempts(3)
                .build();
        long count = atomicLong.getAndIncrement();
        if (count % 3 > 0) {
            log.info("test error===>{}", count);
            throw new RuntimeException("test error");
        }
        return "success ,count=" + count;
    }
}
