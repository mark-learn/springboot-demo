package com.mark.demo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author mark
 * @date 2023年06月05日 15:03:56
 */
@Slf4j
@Service
public class AsyncTestService {

    @Async
    public void execute(String flag) {
        log.info("asyncTest run ---> {}", flag);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // @Async("myExecutor")
    public void execute2() {
        for (int i = 0; i < 100; i++) {
            log.info("asyncTest run ---> {}", i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
