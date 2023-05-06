package org.feather.distributelock;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
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

    @Bean
    public CuratorFramework getCuratorFramework(){
        RetryPolicy retryPolicy=new ExponentialBackoffRetry(1000,3);
        return CuratorFrameworkFactory.newClient("localhost:2181",retryPolicy);
    }
}
