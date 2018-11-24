package com.imgeek.concurrence;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 功能描述: 通过信号量实现生产者-消费者模式
 *
 * @author sunzhiqiang
 * @create 2018-10-28
 */
public class ProducerConsumerPatternBySemaphore {

    /**
     * 自定义一个阻塞队列
     *
     * @param <T>
     */
    static class MyBlockQueue<T> {

        private Semaphore notFull;
        private Semaphore notEmpty;
        private Semaphore mutex;
        private List<T> queue;
        private final int size;

        public MyBlockQueue(int size) {
            this.size = size;
            notFull = new Semaphore(size);
            notEmpty = new Semaphore(0);
            mutex = new Semaphore(1);
            queue = new LinkedList<>();
        }

        public void put(T t) throws InterruptedException {

            if (queue.size() == size) {
                System.out.println("阻塞队列已满，生产者等待队列不满。");
            }

            notFull.acquire();
            mutex.acquire();
            try {
                queue.add(t);
                System.out.println("生产者放入：" + t);
                notEmpty.release();
            } finally {
                mutex.release();
            }
        }

        public <T> T get() throws InterruptedException {

            if (queue.size() == 0) {
                System.out.println("阻塞队列为空，消费者等待队列不空。");
            }

            notEmpty.acquire();
            mutex.acquire();
            try {
                T t = (T) queue.remove(0);
                System.out.println("消费者消费了：" + t);
                notFull.release();
                return t;
            } finally {
                mutex.release();
            }
        }
    }

    /**
     * 生成者任务
     */
    static class Producer implements Runnable {

        private MyBlockQueue queue;

        Producer(MyBlockQueue queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            Random random = new Random();
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(2);
                    queue.put(random.nextInt(10));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 消费者任务
     */
    static class Consumer implements Runnable {

        private MyBlockQueue queue;

        Consumer(MyBlockQueue queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                    queue.get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
