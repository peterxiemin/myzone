package com.imgeek.locks;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * @author： xiemin
 * @date: 2018-09-17
 */

@Slf4j
public class MyReenTrantLockTest extends BaseLockTest {
    private ILocalLock iLocalLock;

    @Before
    public void setup() {
        iLocalLock = new MyReenTrantLock();
    }

    @Test
    public void lockTest() {
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

    @Test
    public void lockTimeoutTest() {
        myFunctionInterface = () -> {
            if (iLocalLock.lock(1, TimeUnit.MILLISECONDS)) {
                log.info(String.valueOf(++counter));
                iLocalLock.unlock();
            }
        };
        lockTest(myFunctionInterface);
        assertNotEquals(counter, testNum);
    }

    @Test
    public void lockTryTest() {
        myFunctionInterface = () -> {
            if (iLocalLock.trylock()) {
                log.info(String.valueOf(++counter));
                iLocalLock.unlock();
            }
        };
        lockTest(myFunctionInterface);
        assertNotEquals(counter, testNum);
    }
}