package com.imgeek.concurrence;

public class ProducerConsumerPatternByConditionTest {

    /**
     * 客户端类
     *
     * @param args
     */
    public static void main(String[] args) {

        ProducerConsumerPatternByCondition.MyBlockQueue<Integer> queue = new ProducerConsumerPatternByCondition.MyBlockQueue<>(10);

        new Thread(new ProducerConsumerPatternByCondition.Producer(queue)).start();
        new Thread(new ProducerConsumerPatternByCondition.Consumer(queue)).start();
        new Thread(new ProducerConsumerPatternByCondition.Consumer(queue)).start();
        new Thread(new ProducerConsumerPatternByCondition.Producer(queue)).start();
    }
}