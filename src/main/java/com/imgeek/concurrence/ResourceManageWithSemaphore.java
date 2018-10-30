package com.imgeek.concurrence;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: peterxiemin
 * @date: 2018/10/30 7:20
 * @desc: semaphore计数锁
 * permits = 0 类似于乐观锁 trylock
 * permits = 1 类似于悲观锁 lock(block)
 **/


@Slf4j
public class ResourceManageWithSemaphore {
    private final Semaphore semaphore;
    private boolean resourceArray[];
    private final ReentrantLock lock;

    public ResourceManageWithSemaphore() {
        this.resourceArray = new boolean[10];//存放厕所状态
        this.semaphore = new Semaphore(10, true);//控制10个共享资源的使用，使用先进先出的公平模式进行共享;公平模式的信号量，先来的先获得信号量
        this.lock = new ReentrantLock(true);//公平模式的锁，先来的先选
        for (int i = 0; i < 10; i++) {
            resourceArray[i] = true;//初始化为资源可用的情况
        }
    }

    public void useResource(int userId) throws InterruptedException {
        semaphore.acquire();
        try {
            int id = getResourceId();//占到一个坑
            log.info("userId:" + userId + "正在使用资源，资源id:" + id + "\n");
            Thread.sleep(100);//do something，相当于于使用资源
            resourceArray[id] = true;//退出这个坑
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();//释放信号量，计数器加1
        }
    }

    /**
     * 这里加入并发控制，因为并发环境下i等变量的增加操作可能会异常
     * @return
     */
    private int getResourceId() {
        int id = -1;
        lock.lock();
        try {
            //lock.lock();//虽然使用了锁控制同步，但由于只是简单的一个数组遍历，效率还是很高的，所以基本不影响性能。
            for (int i = 0; i < 10; i++) {
                if (resourceArray[i]) {
                    resourceArray[i] = false;
                    id = i;
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return id;
    }
}

@Slf4j
class ResourceUser implements Runnable {
    private ResourceManageWithSemaphore resourceManageWithSemaphore;
    private int userId;

    public ResourceUser(ResourceManageWithSemaphore resourceManageWithSemaphore, int userId) {
        this.resourceManageWithSemaphore = resourceManageWithSemaphore;
        this.userId = userId;
    }

    public void run() {
        log.info("userId:" + userId + "准备使用资源...\n");
        try {
            resourceManageWithSemaphore.useResource(userId);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("userId:" + userId + "使用资源完毕...\n");
    }

    public static void main(String[] args) {
        ResourceManageWithSemaphore resourceManage = new ResourceManageWithSemaphore();
        for (int i = 0; i < 100; i++) {
            Thread thread = new Thread(new ResourceUser(resourceManage, i));//创建多个资源使用者
            thread.start();
        }
    }
}
