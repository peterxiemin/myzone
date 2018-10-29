package com.imgeek.concurrence;

public class ProducerConsumerPatternByBlockingQueueTest {

    /**
     * 客户端类
     *
     * @param args
     */
    public static void main(String[] args) {

        ProducerConsumerPatternByBlockingQueue.MyBlockQueue<Integer> queue = new ProducerConsumerPatternByBlockingQueue.MyBlockQueue<>(10);

        new Thread(new ProducerConsumerPatternByBlockingQueue.Producer(queue)).start();
        new Thread(new ProducerConsumerPatternByBlockingQueue.Consumer(queue)).start();
        new Thread(new ProducerConsumerPatternByBlockingQueue.Consumer(queue)).start();
        new Thread(new ProducerConsumerPatternByBlockingQueue.Producer(queue)).start();
    }
}