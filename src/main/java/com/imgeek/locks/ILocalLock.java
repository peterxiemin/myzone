package com.imgeek.locks;

/**
 * @author: xiemin
 * @date: 2018-09-18
 * @desc: 单进程锁
 */

public interface ILocalLock extends IMyLock {
    void await() throws InterruptedException;

    void signalAll();
}
