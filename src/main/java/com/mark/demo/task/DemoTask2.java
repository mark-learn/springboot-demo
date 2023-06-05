package com.mark.demo.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author mark
 * @date 2023年06月05日 14:14:09
 */
@Slf4j
@Component
public class DemoTask2 {

    @Scheduled(cron = "0/10 * * * * *")
    public void execute() throws InterruptedException {
        log.info("---DemoTask2 run------{}", LocalDateTime.now().toString());
        //doASyncTask();
    }


    @Async
    public void doASyncTask() throws InterruptedException {
        for (int i = 0; i < 100; i++) {
           log.info("-----doAsyncTask run-------{}", i);
           Thread.sleep(1000);
        }
    }
}
