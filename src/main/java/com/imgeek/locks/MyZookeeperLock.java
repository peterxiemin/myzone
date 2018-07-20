package com.imgeek.locks;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * auth:    xiemin
 * date:    2018-07-19
 * desc:    基于zookeeper实现的分布式锁
 */

public class MyZookeeperLock implements DistributedLock {
    private static final Logger log = LoggerFactory.getLogger(MyZookeeperLock.class);
    private ZooKeeper zooKeeper;
    private final String rootPath = "/dislock";
    private final String prePath = "lock-";
    private String currentNode = "";
    private static int step = 0;

    public MyZookeeperLock(String host, int sessionTimeout, Watcher watcher) throws IOException, KeeperException, InterruptedException {
        zooKeeper = new ZooKeeper(host, sessionTimeout, watcher);
        init();
    }

    private void init() throws KeeperException, InterruptedException {
        currentNode = createNode();
    }

    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        final int NUM = 50;
        for (int i = 0; i < NUM; i++) {
            Thread thread = new Thread(() -> {
                MyZookeeperLock myZookeeperLock = null;
                try {
                    myZookeeperLock = new MyZookeeperLock("10.0.1.9:2181", 8000, null);
                    myZookeeperLock.lock();
                    System.out.println("step : ".concat(String.valueOf(++step)));
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    myZookeeperLock.unlock();
                }
            });
            thread.start();
        }
    }

    /**
     * 阻塞锁
     *
     * @throws Exception
     */
    @Override
    public void lock() throws Exception {
        while (true) {
            List<String> childNodes = getSortedChildNode();
            int index = getSelfIndexofChildrenNodes(childNodes, currentNode);
            log("currentNode : ".concat(currentNode).concat(" index : ").concat(String.valueOf(index)));
            if (index <= 0) {
                break;
            } else {
                // 如果次小的节点被删除了，则表示当前客户端的节点应该是最小的了，所以使用CountDownLatch来实现等待
                String preNode = getPreSelfNodeOfChildrenNodes(childNodes, currentNode);
                final CountDownLatch latch = new CountDownLatch(1);
                final Watcher previousListener = new Watcher() {
                    public void process(WatchedEvent event) {
                        log("fire Event1 : ".concat(event.getType().toString()));
                        if (event.getType() == Event.EventType.NodeDeleted) {
                            latch.countDown();
                            log("fire Event2 : ".concat(event.getType().toString()));
                        }
                    }
                };
                log("preNode : ".concat(rootPath).concat("/").concat(preNode));
                zooKeeper.exists(rootPath.concat("/").concat(preNode), previousListener);
                latch.await();
                log("".concat("preNode :".concat(preNode)).concat(" awit"));
            }
        }
    }

    @Override
    public void lock(long startmillis, TimeUnit timeUnit) throws Exception {

    }

    @Override
    public boolean trylock() throws Exception {
        return false;
    }

    @Override
    public void unlock() {
        log("unlock :".concat(currentNode));
        if (!currentNode.isEmpty()) {
            try {
                Stat stat = zooKeeper.exists(rootPath.concat("/").concat(currentNode), false);
                if (stat != null) {
                    log("delete : ".concat(rootPath).concat("/").concat(currentNode));
                    zooKeeper.delete(rootPath.concat("/").concat(currentNode), -1);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取排序（升序）后的全部子节点信息
     *
     * @return
     * @throws KeeperException
     * @throws InterruptedException
     */
    private List<String> getSortedChildNode() throws KeeperException, InterruptedException {
        List<String> childrenNodes = zooKeeper.getChildren(rootPath, false);
        childrenNodes.sort(String::compareTo);
        return childrenNodes;
    }

    /**
     * 获取当前节点在全部节点(升序排序后)当中的位置
     *
     * @param childSortedNodes
     * @param selfNode
     * @return
     */
    private int getSelfIndexofChildrenNodes(List<String> childSortedNodes, String selfNode) {
        return childSortedNodes.indexOf(selfNode);
    }

    /**
     * 获取当前节点的前一个节点的名称
     *
     * @param childSortedNodes
     * @param selfNode
     * @return
     */
    private String getPreSelfNodeOfChildrenNodes(List<String> childSortedNodes, String selfNode) {
        int index = getSelfIndexofChildrenNodes(childSortedNodes, selfNode);
        if (index > 0) {
            return childSortedNodes.get(index - 1);
        }
        return null;
    }

    /**
     * 创建节点并获取节点名称
     *
     * @return
     * @throws KeeperException
     * @throws InterruptedException
     */
    private String createNode() throws KeeperException, InterruptedException {
        Stat stat = zooKeeper.exists(rootPath, false);
        if (stat == null) {
            zooKeeper.create(rootPath, null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        }
        String createRet = zooKeeper.create(rootPath.concat("/").concat(prePath), null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        log("create : ".concat(createRet));
        return createRet.substring(createRet.lastIndexOf("/") + 1, createRet.length());
    }

    public static void log(String msg) {
        System.out.println(msg);
    }
}
