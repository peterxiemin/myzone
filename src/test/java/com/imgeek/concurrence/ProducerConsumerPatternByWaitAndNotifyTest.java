package com.imgeek.concurrence;

public class ProducerConsumerPatternByWaitAndNotifyTest {

    /**
     * 客户端类
     *
     * @param args
     */
    public static void main(String[] args) {

        ProducerConsumerPatternByWaitAndNotify.MyBlockQueue<Integer> queue = new ProducerConsumerPatternByWaitAndNotify.MyBlockQueue<>(10);

        new Thread(new ProducerConsumerPatternByWaitAndNotify.Producer(queue)).start();
        new Thread(new ProducerConsumerPatternByWaitAndNotify.Consumer(queue)).start();
        new Thread(new ProducerConsumerPatternByWaitAndNotify.Consumer(queue)).start();
        new Thread(new ProducerConsumerPatternByWaitAndNotify.Producer(queue)).start();
    }
}