package com.imgeek.locks;

import org.junit.Test;

/**
 * @author： xiemin
 * @date:    2018-09-17
 */

public class MyRedisLockTest extends LockTest {
    private int testNum = 50;

    @Test
    public void lockTest() {
        lockDistributedTest(MyRedisLock.class, testNum);
    }
}