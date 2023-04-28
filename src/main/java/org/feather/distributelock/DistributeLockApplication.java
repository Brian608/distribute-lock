package org.feather.distributelock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @projectName: distribute-lock
 * @package: org.feather.distributelock
 * @className: DistributeLockApplication
 * @author: feather(杜雪松)
 * @description: TODO
 * @since: 2023-04-28 18:01
 * @version: 1.0
 */
@SpringBootApplication
public class DistributeLockApplication {
    public static void main(String[] args) {
        SpringApplication.run(DistributeLockApplication.class, args);
    }
}
