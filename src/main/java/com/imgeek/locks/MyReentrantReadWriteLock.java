package com.imgeek.locks;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author :    xiemin
 * @date:       2018-09-18
 * todo
 * 暂没有读锁需求
 */

public class MyReentrantReadWriteLock implements ILocalLock {
    private ReentrantReadWriteLock reentrantReadWriteLock;
    private Condition condition;

    MyReentrantReadWriteLock() {
        reentrantReadWriteLock = new ReentrantReadWriteLock();
        condition = reentrantReadWriteLock.writeLock().newCondition();
    }

    @Override
    public void lock() throws Exception {
        reentrantReadWriteLock.writeLock().lock();
    }

    @Override
    public boolean lock(long startmillis, TimeUnit timeUnit) throws Exception {
        return reentrantReadWriteLock.writeLock().tryLock(startmillis, timeUnit);
    }

    @Override
    public boolean trylock() throws Exception {
        return reentrantReadWriteLock.writeLock().tryLock();
    }

    @Override
    public void unlock() {
        reentrantReadWriteLock.writeLock().unlock();
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
