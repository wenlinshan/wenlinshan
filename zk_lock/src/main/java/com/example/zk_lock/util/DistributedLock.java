package com.example.zk_lock.util;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.ZkConnection;
import org.I0Itec.zkclient.exception.ZkNodeExistsException;
import org.apache.zookeeper.CreateMode;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @author wls
 */
@Component
public class DistributedLock {

    /**
     * 常量
     */
    static class Constant {
        private static final String CONNECTION_STRING = "120.79.4.169:2181";
        private static final String LOCK_NODE = "/distributed_lock";
        private static final String CHILDREN_NODE = "/lock_";
    }

    private final ZkClient zkClient;

    /**
     * 创建zkClient
     */
    public DistributedLock() {
        // 连接到Zookeeper
        zkClient = new ZkClient(new ZkConnection(Constant.CONNECTION_STRING));
        //创建节点
        if (!zkClient.exists(Constant.LOCK_NODE)) {
            zkClient.create(Constant.LOCK_NODE, "分布式锁节点", CreateMode.PERSISTENT);
        }
    }

    /**
     * 获取锁
     *
     * @return 锁名字
     */
    public String getLock() {
        try {
            // 1.在Zookeeper指定节点下创建临时顺序节点
            String lockName = zkClient.createEphemeralSequential(Constant.LOCK_NODE + Constant.CHILDREN_NODE, "");
            // 尝试获取锁
            acquireLock(lockName);
            return lockName;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 尝试获取锁
     *
     * @throws InterruptedException 异常
     */
    public void acquireLock(String lockName) throws InterruptedException {
        // 2.获取lock节点下的所有子节点
        List<String> childrenList = zkClient.getChildren(Constant.LOCK_NODE);
        // 3.对子节点进行排序,获取最小值
        childrenList.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return Integer.parseInt(o1.split("_")[1]) - Integer.parseInt(o2.split("_")[1]);
            }

        });
        // 4.判断当前创建的节点是否在第一位
        int lockPostion = childrenList.indexOf(lockName.split("/")[lockName.split("/").length - 1]);
        if (lockPostion < 0) {
            // 不存在该节点
            throw new ZkNodeExistsException("不存在的节点：" + lockName);
        } else if (lockPostion == 0) {
            // 获取到锁
            System.out.println("获取到锁：" + lockName);
        } else {
            // 未获取到锁，阻塞
            System.out.println("...... 未获取到锁，阻塞等待 。。。。。。");
            // 5.如果未获取得到锁,监听当前创建的节点前一位的节点
            final CountDownLatch latch = new CountDownLatch(1);
            IZkDataListener listener = new IZkDataListener() {
                /**
                 * 当被删除时的监听事件
                 * @param dataPath 节点
                 * @throws Exception 异常
                 */
                @Override
                public void handleDataDeleted(String dataPath) throws Exception {
                    // 6.前一个节点被删除,当不保证轮到自己
                    System.out.println("。。。。。。前一个节点被删除  。。。。。。");
                    acquireLock(lockName);
                    latch.countDown();
                }

                @Override
                public void handleDataChange(String dataPath, Object data) {
                    //节点被改变
                }
            };
            try {
                //监听前一个节点
                zkClient.subscribeDataChanges(Constant.LOCK_NODE + "/" + childrenList.get(lockPostion - 1), listener);
                //阻塞
                latch.await();
            } finally {
                System.out.println("。。。。。取消订阅。。。。。。");
                //取消监听
                zkClient.unsubscribeDataChanges(Constant.LOCK_NODE + "/" + childrenList.get(lockPostion - 1), listener);
            }
        }
    }

    /**
     * 释放锁（删除节点）
     *
     * @param lockName 锁名字
     */
    public void releaseLock(String lockName) {
        zkClient.delete(lockName);
    }

    public void closeZkClient() {
        zkClient.close();
    }
}


