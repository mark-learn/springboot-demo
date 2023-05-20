package com.mark.demo.controller;

import com.mark.demo.service.DemoRetryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author mark
 * @date 2023年02月07日 13:50:56
 */
@Slf4j
@RestController
public class RetryTestController {

    @Autowired
    private DemoRetryService demoRetryService;

    @RequestMapping("/retryTest")
    public String execute() {
        return demoRetryService.executeRetry();
    }


}
