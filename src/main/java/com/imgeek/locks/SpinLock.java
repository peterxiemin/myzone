package com.imgeek.locks;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author: xiemin
 * @date: 2018/10/23 19:19
 * 通过juc包实现自旋锁
 */
public class SpinLock implements ILocalLock {
    AtomicBoolean atomicBoolean = new AtomicBoolean(false);

    public void lock() {
        for (; ; ) {
            if (atomicBoolean.compareAndSet(false, true)) {
                break;
            }
        }
    }

    @Override
    public boolean lock(long startmillis, TimeUnit timeUnit) throws Exception {
        return false;
    }

    @Override
    public boolean trylock() throws Exception {
        return false;
    }

    public void unlock() {
        for (; ; ) {
            if (atomicBoolean.compareAndSet(true, false)) {
                break;
            }
        }
    }

    @Override
    public void await() throws InterruptedException {

    }

    @Override
    public void signalAll() {

    }
}
