package com.imgeek.thread;
/**
 * author:  xiemin
 * date:    2018-07-17
 * desc:    自定义线程安全队列
 */

import java.util.LinkedList;

public class WaitingQueue<E> {
    LinkedList<E> q = new LinkedList<>();

    public synchronized void push(E e) {
        q.add(e);
        this.notify();
    }

    public synchronized E pop() {
        while (q.size() == 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return q.remove();
    }
}
