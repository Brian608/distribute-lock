package org.feather.distributelock.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @projectName: distribute-lock
 * @package: org.feather.distributelock.controller
 * @className: DemoController
 * @author: feather(杜雪松)
 * @description: 单体应用锁
 * @since: 2023-04-28 18:07
 * @version: 1.0
 */
@RestController
@Slf4j
public class DemoController {

    private Lock lock=new ReentrantLock();

    @RequestMapping("/singleLock")
    public String singleLock(){
        log.info("进入了方法");
        lock.lock();;
        log.info("进入了锁");
        try {
            Thread.sleep(60000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
       lock.unlock();
        return "执行完成";
    }
}
