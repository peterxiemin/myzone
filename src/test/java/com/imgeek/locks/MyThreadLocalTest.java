package com.imgeek.locks;

import com.imgeek.jvm.MyFunctionInterface;
import com.imgeek.thread.MyThread;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.omg.CORBA.INTERNAL;

import java.util.concurrent.CountDownLatch;

import static org.junit.Assert.assertEquals;

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