package org.feather.distributelock;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @projectName: distribute-lock
 * @package: org.feather.distributelock
 * @className: DistributeLockApplication
 * @author: feather(杜雪松)
 * @description: TODO
 * @since: 2023-04-28 18:01
 * @version: 1.0
 */
@MapperScan("org.feather.distributelock.dao")
@EnableScheduling
@SpringBootApplication
public class DistributeLockApplication {
    public static void main(String[] args) {
        SpringApplication.run(DistributeLockApplication.class, args);
    }
}
