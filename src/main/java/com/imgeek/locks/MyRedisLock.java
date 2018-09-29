package com.imgeek.locks;

/**
 * auth:    xiemin
 * date:    2018-07-17
 * desc:    redis分布式锁
 */

import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.concurrent.TimeUnit;

import static com.imgeek.locks.RedisUtil.EX;
import static com.imgeek.locks.RedisUtil.NX;
import static java.lang.Thread.currentThread;
import static java.lang.Thread.sleep;

class RedisUtil {
    private Jedis jedis;

    public static final String NX = "nx";//return null if key exist
    public static final String XX = "xx";//return null if key not exist
    public static final String EX = "ex";//秒
    public static final String PX = "px";//毫秒

    public RedisUtil(String host, int port, int timeout, String password) {
        jedis = new Jedis(host, port, timeout);
        jedis.auth(password);
    }

    /**
     * 封装redis setnx命令
     *
     * @param key
     * @param val
     * @param nxxx
     * @param expx
     * @param expire
     * @return
     */
    public boolean set(String key, String val, String nxxx, String expx, long expire) {
        String ret = null;
        try {
            ret = jedis.set(key, val, nxxx, expx, expire);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedis.close();
        }

        return ret != null && ret.equalsIgnoreCase("ok") ? true : false;
    }

    /**
     * 封装redis get命令
     *
     * @param key
     * @return
     */
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

    /**
     * 封装redis del方法
     *
     * @param key
     * @return
     */
    public boolean del(String key) {
        Long ret = null;
        try {
            ret = jedis.del(key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedis.close();
        }
        return ret != null && ret > 0 ? true : false;
    }
}

@Slf4j
class MyRedisLock implements IDistributedLock {
    private String key;
    private String val;
    private int expire;

    private RedisUtil redisUtil;

    public MyRedisLock(String key, String val, int expire, String host, int port, String password) {
        this.redisUtil = new RedisUtil(host, port, 2000, password);
        this.key = key;
        this.val = val;
        this.expire = expire;
    }

    public MyRedisLock() {
        /**
         * todo做成可配置化的
         */
        this.redisUtil = new RedisUtil("10.0.1.9", 6379, 2000, "ruck523.Erin");
        this.key = "key";
        this.val = "val_".concat(String.valueOf(Thread.currentThread()));
        this.expire = 2;
    }

    /**
     * todo 如果网络异常等情况，线程会一直锁死,需要在循环中加入超时
     * 阻塞加锁(悲观锁)
     */
    public void lock() {
        while (true) {
            boolean ret = redisUtil.set(key, val, NX, EX, expire);
            if (ret) {
                break;
            } else {
                try {
                    sleep(10);
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
        return redisUtil.set(key, val, NX, EX, expire);
    }

    /**
     * 加超时的阻塞锁
     *
     * @param startmillis 开始时间戳
     * @param timeUnit
     */
    public boolean lock(long startmillis, TimeUnit timeUnit) {
        return true;
    }

    /**
     * 保证解锁的客户端和之前加锁的客户端是同一个
     */
    public void unlock() {
        String val = redisUtil.get(key);
        if (val != null && val.equalsIgnoreCase(this.val)) {
            log.debug("del val: " + this.val);
            redisUtil.del(key);
        }
        log.debug("only unlock what self concurrence create lock");
    }
}
