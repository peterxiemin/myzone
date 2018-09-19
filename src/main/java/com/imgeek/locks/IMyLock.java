package com.imgeek.locks;

import java.util.concurrent.TimeUnit;

/**
 * auth:    xiemin
 * date:    2018-09-17
 * desc:    锁接口
 */

public interface IMyLock {
    /**
     * 阻塞锁
     */
    void lock() throws Exception;

    /**
     * 加入超时时间的等待锁
     * @param startmillis 开始时间戳
     * @param timeUnit
     */
    boolean lock(long startmillis, TimeUnit timeUnit) throws Exception;

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
