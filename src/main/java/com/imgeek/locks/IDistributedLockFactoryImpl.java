package com.imgeek.locks;

/**
 * auth:    xiemin
 * date:    2018-09-17
 * desc:    分布式锁工厂类实现类
 */

public class IDistributedLockFactoryImpl implements IDistributedLockFactory {
    @Override
    public <T> IDistributedLock getLock(Class<T> c) {
        IDistributedLock iDistributedLock = null;
        try {
            iDistributedLock = (IDistributedLock) Class.forName(c.getName()).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return iDistributedLock;
    }
}
