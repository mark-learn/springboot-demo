package com.mark.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author mark
 * @date 2023年05月21日 01:20:09
 */

@Slf4j
@RestController
public class RequestParamsTestController {

    @RequestMapping("/test/paramTest")
    public Map handleRequest(@RequestParam int id,
                             @RequestParam(required = false) String userName,
                             @RequestParam(required = false) int age,
                             @RequestParam(required = false, defaultValue = "10") int status,
                             @RequestParam(required = false,defaultValue = "true") boolean flag1,
                             @RequestParam(required = false) boolean flag2,
                             @RequestParam(required = false,defaultValue = "false") boolean flag3) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("id", id);
        resultMap.put("userName", userName);
        resultMap.put("age", age);
        resultMap.put("status", status);
        resultMap.put("flag1",flag1);
        resultMap.put("flag2",flag2);
        resultMap.put("flag3",flag3);
        return resultMap;
    }

    @RequestMapping("/test/paramMap")
    public Map handleRequest2(@RequestParam Map<String,String> reqMap) {
        log.info("reqMap====>{}",reqMap);
        return reqMap;
    }
}
