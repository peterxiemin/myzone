package com.imgeek.locks;

import com.imgeek.thread.MyThread;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;

import static org.junit.Assert.*;

public class MyZookeeperLockTest {
    private static int step = 0;

    @Test
    public void demoShow() {
        final int NUM = 50;
        CountDownLatch countDownLatch = new CountDownLatch(NUM);
        for (int i = 0; i < NUM; i++) {
            MyThread myThread = new MyThread(() -> {
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
            myThread.setCountDownLatch(countDownLatch);
            myThread.start();
        }
        try {
            countDownLatch.await();
            System.out.println("main thread awake");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals(step, NUM);
    }
}