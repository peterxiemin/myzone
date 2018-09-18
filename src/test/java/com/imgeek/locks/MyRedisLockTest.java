package com.imgeek.locks;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author： xiemin
 * @date:    2018-09-17
 */

@Slf4j
public class MyRedisLockTest extends BaseLockTest {
    /**
     * 此值过大，会导致redis connection异常
     */
    private int testNum = 50;
    @Before
    public void setup() {
        myFunctionInterface = () -> {
            IDistributedLock iDistributedLock = (new IDistributedLockFactoryImpl()).getLock(MyRedisLock.class);
            iDistributedLock.lock();
            log.info(String.valueOf(++counter));
            iDistributedLock.unlock();
        };
    }

    @Test
    public void lockTest() {
        setTestNum(testNum);
        lockTest(myFunctionInterface);
        assertEquals(counter, testNum);
    }
}