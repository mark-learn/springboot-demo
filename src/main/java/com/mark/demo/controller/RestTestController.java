package com.mark.demo.controller;

import com.mark.demo.api.MarkTestApiClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * @author mark
 * @date 2023年06月07日 10:16:30
 */

@Slf4j
@RestController
@RequestMapping("/rest")
public class RestTestController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private MarkTestApiClient markTestApiClient;

    @RequestMapping("/test")
    public Map handle() {
        Map result = restTemplate.getForObject("http://markabc.top/test", Map.class);
        log.info("result===>{}", result);
        return result;
    }

    @RequestMapping("/test2")
    public Map test2() {
        Map result = markTestApiClient.test();
        log.info("test2===>{}", result);
        return result;
    }
}
