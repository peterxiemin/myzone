package com.imgeek.locks;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

@Slf4j
public class MyReentrantReadWriteLockTest extends BaseLockTest {
    private ILocalLock iLocalLock;

    @Before
    public void setup() {
        iLocalLock = new MyReentrantReadWriteLock();
    }

    @Test
    public void lockReadWriteTest() {
        myFunctionInterface = () -> {
            iLocalLock.lock();
            log.info(String.valueOf(++counter));
            if (counter % 99 == 0) {
                (iLocalLock).await();
                log.info("awake...");
            } else {
                (iLocalLock).signalAll();
            }
            iLocalLock.unlock();
        };
        lockTest(myFunctionInterface);
        assertEquals(counter, testNum);
    }
}