package com.imgeek.concurrence;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.File;
import java.net.URL;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import static org.junit.Assert.assertEquals;

/**
 * @author: xiemin
 * @date: 2018/9/29 9:33
 * 经典多线程搜索某个目录下文件中的关键字个数
 */

@Slf4j
public class MyFileCounterTest {
    @Test
    public void FutureTaskTest() throws ExecutionException, InterruptedException {
        String keywords = "hello world";
        URL url = MyFileCounterTest.class.getClassLoader().getResource("future");
        MyFileCounter myFileCounter = new MyFileCounter(new File(url.getFile()), keywords);
        FutureTask<Integer> futureTask = new FutureTask<>(myFileCounter);
        Thread thread = new Thread(futureTask);
        thread.start();
        assertEquals(3, (int)futureTask.get());
    }
}


