package com.mark.demo.controller;


import com.alibaba.fastjson2.JSON;
import com.mark.demo.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author mark
 * @date 2023年06月06日 18:01:29
 */
@Slf4j
@RestController
@RequestMapping("/redis")
public class RedisTestController {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisUtil redisUtil;

    @RequestMapping("/test")
    public Object test(@RequestParam(required = false, defaultValue = "zhangsan") String name) {
        redisTemplate.opsForValue().set("name", name);
        Object value = redisTemplate.opsForValue().get("name");
        log.info("name===>{} value===>{}", name, value);
        return value;
    }

    @RequestMapping("/test2")
    public Object test22(@RequestParam(required = false, defaultValue = "lisi") String name) {
        Map data = new HashMap();
        data.put("name", "zhangsna");
        data.put("age", 21);
        stringRedisTemplate.opsForValue().set("user", JSON.toJSONString(data));
        String value = stringRedisTemplate.opsForValue().get("user");
        return value;
    }

    @RequestMapping("/test3")
    public Object test() {
        Map map = new HashMap();
        map.put("name", "张三");
        map.put("nickName", "zhangsan");
        map.put("tel", "13512343456");
        redisTemplate.opsForValue().set("userInfo", map);
        return "ok";
    }

    @RequestMapping("/test3/get")
    public Object test3Get() {
        Object value = redisTemplate.opsForValue().get("userInfo");
        log.info("value===>{}", value);
        return value;
    }

    @RequestMapping("/test4")
    public Object teste4() {
        redisUtil.set("mark", "mark2022");
        redisUtil.set("mark60", "markt6", 60);
        Object mark = redisUtil.get("mark");
        Object mark60 = redisUtil.get("mark60");
        long mark60Ttl = redisUtil.getExpire("mark60");
        return "mark=" + mark + " ,mark60=" + mark60 + " ,mark60Ttl=" + mark60Ttl;
    }

    @RequestMapping("/test5")
    public Object test5(@RequestParam(required = false, name = "prefix", defaultValue = "") String keyPrefix) {
        Set<String> keys = redisUtil.keys(keyPrefix);
        return "keys=>" + keys;
    }
}
