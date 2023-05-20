package com.mark.controller;

import com.mark.service.RetryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author mark
 * @date 2023年02月07日 13:50:56
 */
@Slf4j
@RestController
public class RetryTestController {

    @Autowired
    private RetryService retryService;

    @RequestMapping("/retryTest")
    public String execute() {
        return retryService.executeRetry();
    }


}
