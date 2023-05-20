package com.mark.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author mark
 * @date 2023年05月21日 01:20:09
 */

@RestController
public class RequestParamsTestController {

    @RequestMapping("/test/paramTest")
    public Map handleRequest(@RequestParam int id,
                             @RequestParam(required = false) String userName,
                             @RequestParam(required = false) int age,
                             @RequestParam(required = false, defaultValue = "10") int status) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("id", id);
        resultMap.put("userName", userName);
        resultMap.put("age", age);
        resultMap.put("status", status);
        return resultMap;
    }
}
