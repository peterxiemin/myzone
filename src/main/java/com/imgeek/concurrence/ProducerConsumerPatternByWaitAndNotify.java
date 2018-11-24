package com.imgeek.concurrence;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 功能描述: 通过Object的wait/notifyAll机制实现生产者-消费者模式
 *
 * @author sunzhiqiang
 * @create 2018-10-28
 */
public class ProducerConsumerPatternByWaitAndNotify {

    /**
     * 自定义一个阻塞队列
     *
     * @param <T>
     */
    static class MyBlockQueue<T> {

        private Object lock = new Object();

        private List<T> list = new LinkedList<>();
        private final int size;

        public MyBlockQueue(int size) {
            this.size = size;
        }

        public void put(T t) throws InterruptedException {
            synchronized (lock) {
                while (list.size() == size) {
                    System.out.println("阻塞队列已满，生产者等待队列不满。");
                    lock.wait();
                }
                list.add(t);
                System.out.println("生产者放入：" + t);
                lock.notifyAll();
            }
        }

        public <T> T get() throws InterruptedException {
            synchronized (lock) {
                while (list.size() == 0) {
                    System.out.println("阻塞队列为空，消费者等待队列不空。");
                    lock.wait();
                }
                T t = (T) list.remove(0);
                System.out.println("消费者消费了：" + t);
                lock.notifyAll();
                return t;
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
                    TimeUnit.SECONDS.sleep(1);
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
                    TimeUnit.SECONDS.sleep(2);
                    queue.get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
