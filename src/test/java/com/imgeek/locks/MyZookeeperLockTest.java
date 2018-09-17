package com.imgeek.locks;

import org.junit.Test;

/**
 * @author： xiemin
 * @date:    2018-09-17
 */

public class MyZookeeperLockTest extends LockTest {
    private int testNum = 50;

    @Test
    public void lockTest() {
        lockDistributedTest(MyZookeeperLock.class, testNum);
    }
}
