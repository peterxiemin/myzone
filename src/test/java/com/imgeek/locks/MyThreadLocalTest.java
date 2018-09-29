package com.imgeek.locks;

import com.imgeek.jvm.MyFunctionInterface;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author :xiemin
 * @date: 2018-09-19
 */
@Slf4j
public class MyThreadLocalTest extends BaseLockTest {
    private MyFunctionInterface myFunctionInterface;
    private Object lock = new Object();
    private MyThreadLocal<Long> myThreadLocal = new MyThreadLocal<>();

    @Before
    public void setUp() throws Exception {
        myFunctionInterface = () -> {
            synchronized (lock) {
                Long threadId = myThreadLocal.get();
                if (threadId == null) {
                    myThreadLocal.set(Thread.currentThread().getId());
                }
                log.info("thread_id :{}, threadlocal_id:{}", Thread.currentThread().getId(), myThreadLocal.get());
                assertEquals(Thread.currentThread().getId(), (long) myThreadLocal.get());
            }
        };
    }

    @Test
    public void ThreadLocalTest() {
        lockTest(myFunctionInterface);
    }
}