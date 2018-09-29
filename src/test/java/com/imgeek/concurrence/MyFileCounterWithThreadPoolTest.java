package com.imgeek.concurrence;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.File;
import java.net.URL;
import java.util.concurrent.*;

import static org.junit.Assert.assertEquals;

/**
 * @author: xiemin
 * @date: 2018/9/29 16:56
 */
@Slf4j
public class MyFileCounterWithThreadPoolTest {
    @Test
    public void FutureTaskTest() throws ExecutionException, InterruptedException {
        String keywords = "hello world";

        ExecutorService pool = Executors.newCachedThreadPool();
        URL url = MyFileCounterTest.class.getClassLoader().getResource("future");

        MyFileCounterWithThreadPool myFileCounterWithThreadPool = new MyFileCounterWithThreadPool(new File(url.getFile()), keywords, pool);
        Future<Integer> future = pool.submit(myFileCounterWithThreadPool);
        assertEquals(3, (int) future.get());

        pool.shutdown();
        int largestPoolSize = ((ThreadPoolExecutor) pool).getLargestPoolSize();
        log.info("maxPoolSize : {}", largestPoolSize);
    }
}