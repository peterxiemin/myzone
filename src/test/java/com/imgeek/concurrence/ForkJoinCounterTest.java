package com.imgeek.concurrence;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.ForkJoinPool;

import static org.junit.Assert.*;

/**
 * @author: xiemin
 * @date: 2018/9/29 18:35
 */

@Slf4j
public class ForkJoinCounterTest {
    private int MAXNUM = 100000000;
    private int GROUPNUM = 500;
    double[] d = new double[MAXNUM];
    @Before
    public void setup() {
        for (int i = 0; i < MAXNUM; i++) {
            d[i] = Math.random();
        }
    }

    @Test
    public void forkJoinCounterTest() {
        Filter filter = (var) -> var < 0.5 ? true : false;
        ForkJoinCounter forkJoinCounter = new ForkJoinCounter(d,0, MAXNUM, filter, GROUPNUM);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        forkJoinPool.invoke(forkJoinCounter);
        int count = forkJoinCounter.join();
        log.info(String.valueOf(count));
    }

}