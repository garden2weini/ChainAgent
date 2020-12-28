package com.bif.sandbox.redis.service;

import org.redisson.api.RLock;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.StringCodec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.redisson.config.Config;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class DemoHandler {

    @Autowired
    private RedissonClient redisson;

    @GetMapping(value = "/redisson/{key}")
    public String redissonTest(@PathVariable("key") String lockKey) {
        RLock lock = redisson.getLock(lockKey);
        try {
            lock.lock();
            Thread.sleep(10000);
        } catch (Exception e) {

        } finally {
            lock.unlock();
        }
        return "已解锁."+ lockKey;
    }

    @Scheduled(fixedDelay = 10_000, initialDelay = 30_000)
    public final void scheduled() {
        System.out.println("Hello Redis!start");
        Config config = redisson.getConfig();

        RMap<String, String> m = redisson.getMap("test", StringCodec.INSTANCE);
        for(Object val : m.values().toArray()) {
            System.out.println("Key4Test-values:" + val);
        }
        for(int i=0; i<10; i++) {
            m.put("1"+i, "2"+i);
        }

        System.out.println("Hello Redis!end");
    }

}
