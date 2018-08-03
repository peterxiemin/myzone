package com.imgeek.thread;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;

import static org.junit.Assert.*;

public class MyThreadTest {
    private static int step = 0;
    @Test
    public void demoShow() {
        int NUM = 10;
        CountDownLatch countDownLatch = new CountDownLatch(NUM);
        for (int i = 0; i < NUM; i++) {
            MyThread myThread = new MyThread(
                    () -> {
                        ++step;
                    }
            );
            myThread.setCountDownLatch(countDownLatch);
            myThread.start();
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals(step, NUM);
    }
}