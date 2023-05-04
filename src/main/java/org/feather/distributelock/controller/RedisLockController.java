package org.feather.distributelock.controller;

import lombok.extern.slf4j.Slf4j;
import org.feather.distributelock.lock.RedisLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * @projectName: distribute-lock
 * @package: org.feather.distributelock.controller
 * @className: RedisLockController
 * @author: feather(杜雪松)
 * @description: TODO
 * @since: 2023-05-04 17:25
 * @version: 1.0
 */
@Slf4j
@RestController
public class RedisLockController {

    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("/redisLock")
    public String redisLock(){
        log.info("我进入了方法！");
        try (RedisLock redisLock = new RedisLock(redisTemplate,"redisKey",30)){
            if (redisLock.getLock()) {
                log.info("我进入了锁！！");
                Thread.sleep(15000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("方法执行完成");
        return "方法执行完成";
    }
}
