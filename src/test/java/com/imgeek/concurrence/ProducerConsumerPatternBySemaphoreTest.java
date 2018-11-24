package com.imgeek.concurrence;

public class ProducerConsumerPatternBySemaphoreTest {


    /**
     * 客户端类
     *
     * @param args
     */
    public static void main(String[] args) {

        ProducerConsumerPatternBySemaphore.MyBlockQueue<Integer> queue = new ProducerConsumerPatternBySemaphore.MyBlockQueue<>(10);

        new Thread(new ProducerConsumerPatternBySemaphore.Producer(queue)).start();
        new Thread(new ProducerConsumerPatternBySemaphore.Consumer(queue)).start();
        new Thread(new ProducerConsumerPatternBySemaphore.Consumer(queue)).start();
        new Thread(new ProducerConsumerPatternBySemaphore.Producer(queue)).start();
    }
}