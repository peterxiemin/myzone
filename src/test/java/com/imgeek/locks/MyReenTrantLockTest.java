package com.imgeek.locks;

import org.junit.Before;
import org.junit.Test;

/**
 * @author： xiemin
 * @date:    2018-09-17
 */

public class MyReenTrantLockTest extends LockTest {
    private int testNum = 500;
    private MyReenTrantLock myReenTrantLock;

    @Before
    public void setup() {
        myReenTrantLock = new MyReenTrantLock();
    }

    @Test
    public void lockTest() {
        lockTest(myReenTrantLock, testNum);
    }
}