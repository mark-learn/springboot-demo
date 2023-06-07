package com.mark.demo.controller;

import com.mark.demo.service.AsyncTestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @Autowired(required = false)
    private ThreadPoolTaskExecutor taskExecutor;

    @Autowired
    private AsyncTestService asyncTestService;

    private AtomicInteger atomicInt = new AtomicInteger(0);

    @RequestMapping("/test/async")
    public String doAsync(@RequestParam(required = false, defaultValue = "500") int count) {
        int seqNum = this.atomicInt.incrementAndGet();
        for (int idx = 0; idx < count; idx++) {
            asyncTestService.execute(seqNum + "-" + idx);
        }
        return "ok, seqNum:" + seqNum;
    }

    @RequestMapping("/test/async/stat")
    public Map<String, Object> getStatInfo() {
        Map<String, Object> statMap = new HashMap<>();
        statMap.put("corePoolSize", taskExecutor.getCorePoolSize());
        statMap.put("maxPoolSize", taskExecutor.getMaxPoolSize());
        statMap.put("poolSize", taskExecutor.getPoolSize());
        statMap.put("queueCapacity", taskExecutor.getQueueCapacity());
        statMap.put("activeCount", taskExecutor.getActiveCount());
        statMap.put("queueSize", taskExecutor.getQueueSize());
        statMap.put("threadNamePrefix", taskExecutor.getThreadNamePrefix());
        log.info("statMap===>{}", statMap);
        return statMap;
    }
}
