package org.feather.distributelock.controller;

import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.C;
import org.feather.distributelock.lock.RedisLock;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

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

    @GetMapping("/redissonLock")
    public String redissonLock(){
        Config config =new Config();
        config.useSingleServer().setAddress("redis://localhost:6379");
        RedissonClient redissonClient= Redisson.create(config);
        RLock rLock=redissonClient.getFairLock("order");
        log.info("我进入了方法");
        try {
            rLock.lock(30, TimeUnit.SECONDS);
            log.info("我获得了锁");
            Thread.sleep(10000);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            log.info("我释放了锁");
            rLock.unlock();;
        }
        log.info("方法执行完成");
        return "方法执行完成";
    }
}
