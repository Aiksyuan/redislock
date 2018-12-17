package com.ly.redislock.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class LockService {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public boolean lock(String key, String value) {
        if (redisTemplate.opsForValue().setIfAbsent(key, value)) {
            System.out.println("加锁成功=====================");
            return true;
        }
        String currentValue = redisTemplate.opsForValue().get(key);
        if (!StringUtils.isEmpty(currentValue) && Long.parseLong(currentValue) < System.currentTimeMillis()) {
            String oldValue = redisTemplate.opsForValue().getAndSet(key, value);
            if (!StringUtils.isEmpty(oldValue) && oldValue.equals(currentValue)) {
                System.out.println("加锁成功=====================");
                return true;
            }
        }
        return false;
    }

    public void unLook(String key, String value) {
        try {
            String currentValue = redisTemplate.opsForValue().get(key);
            if (!StringUtils.isEmpty(currentValue) && currentValue.equals(value)) {
                redisTemplate.opsForValue().getOperations().delete(key);
                System.out.println("解锁成功=====================");
            }
        } catch (Exception e) {
            System.out.println("解锁失败！");
        }
    }
}

