package com.imgeek.locks;

import com.imgeek.thread.MyThread;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;

import static java.lang.Thread.currentThread;
import static org.junit.Assert.*;

public class MyRedisLockTest {
    private static int step;

    @Test
    public void demoShow() {

        final int NUM = 50;
        CountDownLatch countDownLatch = new CountDownLatch(NUM);
        for (int i = 0; i < NUM; i++) {
            MyThread myThread = new MyThread(() -> {
                MyRedisLock myRedisLock = new MyRedisLock("key", "val_".concat(currentThread().getName()), 2, "10.0.1.9", 6379, "ruck523.Erin");
                myRedisLock.lock();
                System.out.println((++step));
                myRedisLock.unlock();
            });
            myThread.setCountDownLatch(countDownLatch);
            myThread.start();
        }
        try {
            countDownLatch.await();
            System.out.println("main thread awake...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals(step, NUM);
    }
}