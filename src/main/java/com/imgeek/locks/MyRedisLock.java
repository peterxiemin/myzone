package com.imgeek.locks;

/**
 * auth:    xiemin
 * date:    2018-07-17
 * desc:    redis分布式锁
 */

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.concurrent.TimeUnit;

import static java.lang.Thread.currentThread;
import static java.lang.Thread.sleep;

class MyRedisLock implements DistributedLock {
    private static final String NX = "nx";//return null if key exist
    private static final String XX = "xx";//return null if key not exist
    private static final String EX = "ex";//秒
    private static final String PX = "px";//毫秒

    private JedisPool pool;
    private String _key;
    private String _val;
    private int _expire;

    public MyRedisLock(String key, String val, int expire, String host, int port, String password) {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxWaitMillis(0);
        jedisPoolConfig.setMaxTotal(20);//必须大于线程数，否则会出现getResource失败的情况
        pool = new JedisPool(jedisPoolConfig, host, port, 2000, password);
        _key = key;
        _val = val;
        _expire = expire;
    }

    /**
     * 阻塞加锁(悲观锁)
     */
    public void lock() {
        int i = 0;
        while (true) {
            Jedis jedis = pool.getResource();
            String opt_ret = jedis.set(_key, _val, NX, EX, _expire);

            log(" i: " + (++i) + " opt_ret: " + opt_ret);
            if (opt_ret != null && opt_ret.equalsIgnoreCase("ok")) {
                jedis.close();
                break;
            } else {
                try {
                    sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            jedis.close();
        }
    }

    /**
     * 非阻塞加锁(乐观锁)
     *
     * @return
     */
    public boolean trylock() {
        Jedis jedis = pool.getResource();
        String opt_ret = jedis.set(_key, _val, NX, EX, _expire);
        if (opt_ret != null && opt_ret.equalsIgnoreCase("ok")) {
            jedis.close();
            return true;
        }
        jedis.close();
        return false;
    }

    /**
     * 加超时的阻塞锁
     * @param startmillis 开始时间戳
     * @param timeUnit
     */
    public void lock(long startmillis, TimeUnit timeUnit) {

    }

    /**
     * 保证解锁的客户端和之前加锁的客户端是同一个
     */
    public void unlock() {
        Jedis jedis = pool.getResource();
        String val = jedis.get(_key);
        if (val != null && val.equalsIgnoreCase(_val)) {
            log("del val: " + val);
            jedis.del(_key);
        }
        jedis.close();
    }

    /**
     * 打印线程号和内容
     *
     * @param msg
     */
    private void log(String msg) {
        System.out.println("thread-" + currentThread().getId() + " msg: " + msg);
    }

    public static void main(String[] args) {
        int NUM = 10;
        MyRedisLock myRedisLock = new MyRedisLock("key", "val", 10, "10.0.1.9", 6379, "ruck523.Erin");
        for (int i = 0; i < NUM; i++) {
            Thread thread = new Thread(
                    () -> {
                        if (myRedisLock.trylock()) {
                            myRedisLock.unlock();
                        }
                    }
            );
            thread.start();
        }
    }
}
