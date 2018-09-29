package com.imgeek.concurrence;

import com.imgeek.jvm.MyFunctionInterface;
import com.imgeek.locks.BaseLockTest;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author： xiemin
 * @date:    2018-09-17
 */

public class WaitingQueueTest extends BaseLockTest {
    private WaitingQueue<Integer> waitingQueue;
    private MyFunctionInterface myFunctionInterface;

    @Before
    public void setup() {
        waitingQueue = new WaitingQueue<>();
        myFunctionInterface = () -> {
            waitingQueue.push(1);
            waitingQueue.push(1);
            waitingQueue.pop();
        };
    }

    @Test
    public void waitQueueTest() {
        lockTest(myFunctionInterface);
        assertEquals(waitingQueue.size(), testNum);
    }
}