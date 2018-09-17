package com.imgeek.locks;

import com.imgeek.thread.MyThread;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;

import static org.junit.Assert.assertEquals;

/**
 * @author： xiemin
 * @date:    2018-09-17
 */

@Slf4j
public class LockTest {
    private int counter = 0;

    public void lockTest(IMyLock iMyLock, int num) {
        CountDownLatch countDownLatch = new CountDownLatch(num);
        for (int i = 0; i < num; i++) {
            MyThread myThread = new MyThread(() -> {
                iMyLock.lock();
                log.info(String.valueOf(++counter));
                if (iMyLock instanceof MyReenTrantLock) {
                    if (counter % 99 == 0) {
                        ((MyReenTrantLock) iMyLock).await();
                    } else {
                        ((MyReenTrantLock) iMyLock).signalAll();
                    }
                }
                iMyLock.unlock();
                countDownLatch.countDown();
            });
            myThread.start();
        }
        try {
            countDownLatch.await();
            log.info("main thread awake...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals(counter, num);
    }

    public <T> void lockDistributedTest(Class<T> c, int num) {
        CountDownLatch countDownLatch = new CountDownLatch(num);
        for (int i = 0; i < num; i++) {
            MyThread myThread = new MyThread(() -> {
                IDistributedLockFactory iDistributedLockFactory = new IDistributedLockFactoryImpl();
                IDistributedLock iDistributedLock = iDistributedLockFactory.getLock(c);
                iDistributedLock.lock();
                log.info(String.valueOf(++counter));
                iDistributedLock.unlock();
                countDownLatch.countDown();
            });
            myThread.start();
        }
        try {
            countDownLatch.await();
            log.info("main thread awake...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals(counter, num);
    }
}
