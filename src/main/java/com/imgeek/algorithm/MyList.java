package com.imgeek.algorithm;

/**
 * auth:    xiemin
 * date:    2018-08-04
 * date:    链表
 */

public class MyList {
    private Node head = null;
    private int size = 0;

    class Node {
        Node prev;
        Node next;
        int data;
    }

    public void add(int data) {
        Node node = new Node();
        node.data = data;
        if (node == null) throw new NullPointerException();
        if (head == null) {
            head = node;
            head.prev = head.next = null;
        } else {
            for (Node itr = head; itr != null; itr = itr.next) {
                if (itr.next == null) {
                    itr.next = node;
                    node.prev = itr;
                    node.next = null;
                    break;//链表内容已经改变，需要立刻跳出，否则循环条件已经被破坏
                }
            }
        }
        ++size;
    }

    public boolean contains(int data) {
        Node node = new Node();
        node.data = data;
        for (Node itr = head; itr != null; itr = itr.next) {
            if (node.data == itr.data) {
                return true;
            }
        }
        return false;
    }

    public boolean remove(int data) {
        Node node = new Node();
        node.data = data;
        for (Node itr = head; itr != null; itr = itr.next) {
            if (node.data == itr.data) {
                itr.prev.next = itr.next;
                if (itr.next != null) {//删除最后一个节点
                    itr.next.prev = itr.prev;
                }
                --size;
                return true;
            }
        }
        return false;
    }

    public int get(int i) {
        if (i > size) throw new IndexOutOfBoundsException("i:".concat(String.valueOf(i)).concat(" size:").concat(String.valueOf(size)));
        int j = 0;
        for (Node itr = head; itr != null; itr = itr.next) {
            if ((j++) == i) {
                return itr.data;
            }
        }
        return 0;
    }

    public int size() {
        return size;
    }
}
