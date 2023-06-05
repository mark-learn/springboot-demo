package com.mark.demo.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author mark
 * @date 2023年06月05日 14:14:09
 */
@Slf4j
@Component
public class DemoTask1 {

    @Scheduled(cron = "0/5 * * * * *")
    public void execute() throws InterruptedException {
        log.info("---DemoTask1 run------{}", LocalDateTime.now().toString());
        Thread.sleep(30000);
        log.info("---DemoTask1 end-----{}", LocalDateTime.now().toString());
    }
}
