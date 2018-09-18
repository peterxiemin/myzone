package com.imgeek.locks;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: xiemin
 * @date: 2018-09-17
 * @desc: java.util.concurrent提供的锁
 */


public class MyReenTrantLock implements ILocalLock {
    private ReentrantLock reentrantLock;

    private Condition condition;

    public MyReenTrantLock() {
        reentrantLock = new ReentrantLock();
        condition = reentrantLock.newCondition();
    }

    /**
     * 听起来公平锁更合理一些，但是使用公平锁比使用常规锁要慢很多，只有当你确实
     * 了解自己要做什么并且对于你要解决的问题有一定特定理由必须使用公平锁的时候
     * ，才可以使用公平锁，实际使用了公平锁，也无法确保线程调度是公平的，如果线
     * 程调度器选择忽略一个线程，而该线程为了这个锁已经等了很长时间，就没有机会
     * 公平地处理这个锁了。
     *
     * @param fair
     */
    public MyReenTrantLock(boolean fair) {
        reentrantLock = new ReentrantLock(fair);
    }

    @Override
    public void lock() throws Exception {
        reentrantLock.lock();
    }

    @Override
    public boolean lock(long startmillis, TimeUnit timeUnit) throws Exception {
        return reentrantLock.tryLock(startmillis, timeUnit);
    }

    @Override
    public boolean trylock() throws Exception {
        return reentrantLock.tryLock();
    }

    @Override
    public void unlock() {
        reentrantLock.unlock();
    }

    @Override
    public void await() throws InterruptedException {
        condition.await();
    }

    @Override
    public void signalAll() {
        condition.signalAll();
    }
}