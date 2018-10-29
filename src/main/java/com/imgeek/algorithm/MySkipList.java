package com.imgeek.algorithm;

import lombok.extern.slf4j.Slf4j;

/**
 * @author: xiemin
 * @date: 2018/10/29 10:45
 * not thread safe
 */

@Slf4j
public class MySkipList<E extends Comparable<E>> {

    private int headLevel;
    private Node head;

    public MySkipList() {
        head = new Node();
    }

    private class Node implements Comparable<Node> {
        E data;
        Node left;
        Node low;

        @Override
        public int compareTo(Node o) {
            return this.data.compareTo(o.data);
        }
    }

    /**
     * 查询
     */
    boolean find(E data) {
        return true;
    }

    /**
     * 删除
     */
    boolean remove(E data) {
        return true;
    }

    /**
     * 添加
     */
    boolean add(E data) {
        int level = getLevel();
        Node leftHead = null;
        if (headLevel < level) {
            for (int i = headLevel; i < level; i++) {
                Node node = new Node();
                node.data = null;
                node.low = head;
                head = node;
            }
            headLevel = level;
            leftHead = head;
        } else {
            leftHead = head;
            for (int i = headLevel; i > level; i--) {
                leftHead = leftHead.low;
            }
        }

        for (Node insedNode = null; leftHead != null; leftHead = leftHead.low) {
            Node insNode = new Node();
            insNode.data = data;
            insNode.low = insNode.left = null;
            if (insedNode != null) insedNode.low = insNode;

            Node curNode = leftHead;
            for (; curNode.left != null; curNode = curNode.left) {
                if (insNode.compareTo(curNode.left) < 0) {
                    insNode.left = curNode.left;
                    curNode.left = insNode;
                    break;
                }
            }
            if (null == curNode.left) curNode.left = insNode;
            insedNode = insNode;
        }
        return true;
    }

    /**
     * 测试使用
     */
    public void traverse() {
        int line = headLevel;
        Node leftHead = head;
        for (; leftHead != null; leftHead = leftHead.low, line--) {
            int size = 0;
            for (Node curNode = leftHead; curNode.left != null; curNode = curNode.left) {
                size++;
            }
            log.info("level: " + line + " size: " + size);
        }
    }

    /**
     * 获取level
     */
    private int getLevel() {
        int k = 0;
        while (Math.random() < 0.5) {
            k++;
        }
        return k;
    }
}
