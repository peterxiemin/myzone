package com.imgeek.locks;

/**
 * auth:    xiemin
 * date:    2018-09-17
 * desc:    分布式锁工厂类
 */

public interface IDistributedLockFactory {
    <T> IDistributedLock getLock(Class<T> c);
}
