package com.imgeek.locks;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author: xiemin
 * @date: 2018-09-18
 */

@Slf4j
public class MySynchronizedTest extends BaseLockTest {
    private Object lock = new Object();
    @Test
    public void synchronizedTest() {
        myFunctionInterface = () -> {
            synchronized (lock) {
                log.info(String.valueOf(++counter));
            }
        };
        lockTest(myFunctionInterface);
        assertEquals(counter, testNum);
    }
}