package com.imgeek.concurrence;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;

import static org.junit.Assert.*;

/**
 * @author： xiemin
 * @date: 2018-09-17
 */

public class MyThreadTest {
    private int step = 0;
    private int NUM = 10;
    private Object lock = new Object();

    @Test
    public void counterTest() {
        CountDownLatch countDownLatch = new CountDownLatch(NUM);
        for (int i = 0; i < NUM; i++) {
            MyThread myThread = new MyThread(
                    () -> {
                        synchronized (lock) {
                            ++step;
                        }
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