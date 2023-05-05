package org.feather.distributelock.lock;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;

/**
 * @projectName: distribute-lock
 * @package: org.feather.distributelock.lock
 * @className: ZkLock
 * @author: feather(杜雪松)
 * @description: TODO
 * @since: 2023-05-05 14:49
 * @version: 1.0
 */
@Slf4j
public class ZkLock  implements  AutoCloseable, Watcher {

    private String znode;

    private ZooKeeper zooKeeper;

    public  ZkLock() throws IOException {
        this.zooKeeper=new ZooKeeper("localhost:2181",10000,this);
    }


    public  Boolean getLock(String businessCode){
        try {
            //创建业务 根节点
            Stat stat = zooKeeper.exists("/" + businessCode, false);
            if (stat==null){
                zooKeeper.create("/" + businessCode,businessCode.getBytes(StandardCharsets.UTF_8),
                        ZooDefs.Ids.OPEN_ACL_UNSAFE,
                        CreateMode.PERSISTENT);
            }

            //创建瞬时有序节点  /order/order_00000001
            znode = zooKeeper.create("/" + businessCode + "/" + businessCode + "_", businessCode.getBytes(StandardCharsets.UTF_8),
                    ZooDefs.Ids.OPEN_ACL_UNSAFE,
                    CreateMode.EPHEMERAL_SEQUENTIAL);

            //获取业务节点下 所有的子节点
            List<String> childrenNodes = zooKeeper.getChildren("/" + businessCode, false);
            //子节点排序
            Collections.sort(childrenNodes);
            //获取序号最小的（第一个）子节点
            String firstNode = childrenNodes.get(0);
            //如果创建的节点是第一个子节点，则获得锁
            if (znode.endsWith(firstNode)){
                return true;
            }
            //不是第一个子节点，则监听前一个节点
            String lastNode = firstNode;
            for (String node:childrenNodes){
                if (znode.endsWith(node)){
                    zooKeeper.exists("/"+businessCode+"/"+lastNode,true);
                    break;
                }else {
                    lastNode = node;
                }
            }
            synchronized (this){
                wait();
            }
            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    @Override
    public void close() throws Exception {
        zooKeeper.delete(znode,-1);
        zooKeeper.close();
        log.info("我已经释放了锁！");
    }

    @Override
    public void process(WatchedEvent event) {
        if (event.getType() == Event.EventType.NodeDeleted){
            synchronized (this){
                notify();
            }
        }
    }
}
