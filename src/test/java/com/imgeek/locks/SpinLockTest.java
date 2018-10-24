package com.imgeek.locks;

import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;

import static org.junit.Assert.*;

/**
 * @author: xiemin
 * @date: 2018/10/24 9:33
 */
public class SpinLockTest {

    private static final int TESTMAX = 10000;

    private int count = 0;

    private SpinLock spinLock;

    @Before
    public void setUp() throws Exception {
        spinLock = new SpinLock();
    }

    @Test
    public void lockTest() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(TESTMAX);
        for (int i = 0; i < TESTMAX; i++) {
            new Thread(() -> {
                spinLock.lock();
                count++;
                spinLock.unlock();
                countDownLatch.countDown();
            }).start();
        }
        countDownLatch.await();
        assertEquals(TESTMAX , count);
    }
}