package com.imgeek.thread;

import com.imgeek.jvm.MyFunctionInterface;

import java.util.concurrent.CountDownLatch;

/**
 * auth:    xiemin
 * date:    2018-07-17
 * desc:    jdk底层线程使用
 */
class Thread1 implements Runnable {

    @Override
    public void run() {

    }
}

class Thread2 extends Thread {
    @Override
    public void run() {

    }
}


public class MyThread extends Thread {
    private MyFunctionInterface functionInterface;
    private CountDownLatch countDownLatch;
    private WaitingQueue<?> waitingQueue;

    public MyThread(MyFunctionInterface functionInterface)
    {
        this.functionInterface = functionInterface;
    }

    public void countDwon() {
        countDownLatch.countDown();
    }

    @Override
    public void run() {
        functionInterface.apply();
        countDwon();
    }

    public void setCountDownLatch(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    public void setWaitingQueue(WaitingQueue<?> waitingQueue) {
        this.waitingQueue = waitingQueue;
    }
}
