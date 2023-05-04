package org.feather.distributelock.service;

import lombok.extern.slf4j.Slf4j;
import org.feather.distributelock.lock.RedisLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * @projectName: distribute-lock
 * @package: org.feather.distributelock.service
 * @className: SchedulerService
 * @author: feather(杜雪松)
 * @description: TODO
 * @since: 2023-05-04 18:20
 * @version: 1.0
 */
@Service
@Slf4j
public class SchedulerService {
    @Autowired
    private RedisTemplate redisTemplate;

    @Scheduled(cron = "0/5 * * * * ?")
    public void sendSms(){
        try(RedisLock redisLock = new RedisLock(redisTemplate,"autoSms",30)) {
            if (redisLock.getLock()){
                log.info("向138xxxxxxxx发送短信！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
