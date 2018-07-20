package com.imgeek.locks;

/**
 * auth:    xiemin
 * date:    2018-07-19
 * desc:    分布式锁接口
 */

import java.util.concurrent.TimeUnit;

public interface DistributedLock {
    /**
     * 阻塞锁
     */
    void lock() throws Exception;

    /**
     * 加入超时时间的等待锁
     * @param startmillis 开始时间戳
     * @param timeUnit
     */
    void lock(long startmillis, TimeUnit timeUnit) throws Exception;

    /**
     * 非阻塞锁
     * @return
     */
    boolean trylock() throws Exception;

    /**
     * 解锁
     */
    void unlock();
}
