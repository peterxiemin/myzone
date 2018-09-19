package com.imgeek.locks;

import com.imgeek.jvm.MyFunctionInterface;
import com.imgeek.thread.MyThread;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;

import static org.junit.Assert.assertEquals;

/**
 * @author： xiemin
 * @date: 2018-09-17
 */

@Slf4j
public class BaseLockTest {
    protected int testNum = 5000;
    protected int counter = 0;
    protected MyFunctionInterface myFunctionInterface;

    public <T> void lockTest(MyFunctionInterface myFunctionInterface) {
        CountDownLatch countDownLatch = new CountDownLatch(testNum);

        for (int i = 0; i < testNum; i++) {
            MyThread myThread = new MyThread(myFunctionInterface);
            myThread.setCountDownLatch(countDownLatch);
            myThread.start();
        }
        try {
            countDownLatch.await();
            log.info("main thread awake...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    protected void setTestNum(int testNum) {
        this.testNum = testNum;
    }
}
