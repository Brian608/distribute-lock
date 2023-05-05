package org.feather.distributelock.controller;

import lombok.extern.slf4j.Slf4j;
import org.feather.distributelock.lock.ZkLock;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @projectName: distribute-lock
 * @package: org.feather.distributelock.controller
 * @className: ZkController
 * @author: feather(杜雪松)
 * @description: TODO
 * @since: 2023-05-05 15:18
 * @version: 1.0
 */
@Slf4j
@RestController
public class ZkController {

    @GetMapping("/zkLock")
    public String zookeeperLock(){
        log.info("我进入了方法！");
        try (ZkLock zkLock = new ZkLock()) {
            if (zkLock.getLock("order")){
                log.info("我获得了锁");
                Thread.sleep(10000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("方法执行完成！");
        return "方法执行完成！";
    }
}
