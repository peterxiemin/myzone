package com.imgeek.locks;

/**
 * auth:    xiemin
 * date:    2018-07-17
 * desc:    redis分布式锁
 */

import redis.clients.jedis.Jedis;

import java.util.concurrent.TimeUnit;

import static java.lang.Thread.currentThread;
import static java.lang.Thread.sleep;

class RedisUtil {
    public static final String NX = "nx";//return null if key exist
    public static final String XX = "xx";//return null if key not exist
    public static final String EX = "ex";//秒
    public static final String PX = "px";//毫秒

    private Jedis jedis;

    public RedisUtil(String host, int port, int timeout, String password) {
        jedis = new Jedis(host, port, timeout);
        jedis.auth(password);
    }

    public String set(String key, String val, String nxxx, String expx, int expire) {
        try {
            return jedis.set(key, val, nxxx, expx, expire);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedis.close();
        }
        return null;
    }

    public String get(String key) {
        try {
            return jedis.get(key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedis.close();
        }
        return null;
    }

    public void del(String key) {
        try {
            jedis.del(key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedis.close();
        }
    }
}

class MyRedisLock implements DistributedLock {
    private RedisUtil redisUtil;
    private String key;
    private String val;
    private int expire;

    public MyRedisLock(RedisUtil redisUtil, String key, String val, int expire) {
        this.key = key;
        this.val = val;
        this.expire = expire;
        this.redisUtil = redisUtil;

    }

    /**
     * 阻塞加锁(悲观锁)
     */
    public void lock() {
        int i = 0;
        while (true) {
            String opt_ret = redisUtil.set(key, val, RedisUtil.NX, RedisUtil.PX, expire);
            if (opt_ret != null && opt_ret.equalsIgnoreCase("ok")) {
                break;
            } else {
                try {
                    sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 非阻塞加锁(乐观锁)
     *
     * @return
     */
    public boolean trylock() {
        String opt_ret = redisUtil.set(key, val, RedisUtil.NX, RedisUtil.PX, expire);
        if (opt_ret != null && opt_ret.equalsIgnoreCase("ok")) {
            return true;
        }
        return false;
    }

    /**
     * 加超时的阻塞锁
     *
     * @param startmillis 开始时间戳
     * @param timeUnit
     */
    public void lock(long startmillis, TimeUnit timeUnit) {

    }

    /**
     * 保证解锁的客户端和之前加锁的客户端是同一个
     */
    public void unlock() {
        String val = redisUtil.get(key);
        if (val != null && val.equalsIgnoreCase(val)) {
            log("del val: " + val);
            redisUtil.del(key);
        }
    }

    /**
     * 打印线程号和内容
     *
     * @param msg
     */
    private void log(String msg) {
        System.out.println("thread-" + currentThread().getId() + " msg: " + msg);
    }

    public static int step = 0;

    public static void main(String[] args) {
        int NUM = 50;
        for (int i = 0; i < NUM; i++) {
            Thread thread = new Thread(
                    () -> {
                        RedisUtil redisUtil = new RedisUtil("10.0.1.9", 6379, 5000, "ruck523.Erin");
                        MyRedisLock myRedisLock = new MyRedisLock(redisUtil, "key", currentThread().getName(), 2000);
                        myRedisLock.lock();
                        System.out.println("step".concat(String.valueOf(++step)));
                        myRedisLock.unlock();
                    }
            );
            thread.start();
        }
    }
}
