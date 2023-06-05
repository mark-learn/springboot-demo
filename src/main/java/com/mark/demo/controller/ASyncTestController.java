package com.mark.demo.controller;

import com.mark.demo.service.AsyncTestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author mark
 * @date 2023年05月21日 01:20:09
 */

@Slf4j
@RestController
public class ASyncTestController {

    @Autowired
    private ThreadPoolTaskExecutor myExecutor;

    @Autowired
    private AsyncTestService asyncTestService;

    private AtomicInteger atomicInt = new AtomicInteger(0);

    @RequestMapping("/test/async")
    public String doAsync() {
        int seqNum = this.atomicInt.incrementAndGet();
        for (int idx = 0; idx < 500; idx++) {
            asyncTestService.execute(seqNum + "-" + idx);
        }
        return "ok, seqNum:" + seqNum;
    }

    @RequestMapping("/test/async/stat")
    public Map<String, Object> getStatInfo() {
        Map<String, Object> statMap = new HashMap<>();
        //获取当前线程池中线程数量
        statMap.put("poolSize", myExecutor.getPoolSize());
        //获取正在执行任务的线程数量
        statMap.put("activeCount", myExecutor.getActiveCount());
        //获取等待执行的任务数量
        statMap.put("queueSize", myExecutor.getQueueSize());
        //获取队列的最大容量
        statMap.put("queueCapacity", myExecutor.getQueueCapacity());
        log.info("statMap===>{}", statMap);
        return statMap;
    }
}
