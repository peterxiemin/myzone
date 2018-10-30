package com.imgeek.algorithm;

import java.util.Arrays;

/**
 * B-Tree
 */
public class BTree<Key extends Comparable<Key>, Value> {

    /**
     * 每个节点中最大的子节点数量、必须是偶数并且大于2
     */
    private final static int M = 16;
    /**
     * 根节点
     */
    private Node root;
    /**
     * 树高
     */
    private int height;
    /**
     * 节点数量
     */
    private int n;

    public BTree() {
        root = new Node(0);
    }

    /**
     * 节点
     */
    private static final class Node implements Comparable<Node> {

        private int m;
        private Entry[] children = new Entry[M];
        private int nodeSize;
        private Node[] childrenNode = new Node[M + 1];
        private Node parent;

        private Node(int k) {
            m = k;
        }

        private Node() {

        }

        private void addEntry(Entry entry) {
            children[m++] = entry;
            Arrays.sort(children, 0, m);
        }

        private void addChildrenNode(Node node) {
            node.parent = this;
            childrenNode[nodeSize++] = node;
            Arrays.sort(childrenNode, 0, nodeSize);
        }

        private void removeChildrenNode(int index) {
            childrenNode[index] = null;
            nodeSize--;
        }


        @Override
        public int compareTo(Node o) {
            return this.children[0].compareTo(o.children[0]);
        }
    }

    /**
     * 节点中的每个
     */
    private static class Entry implements Comparable<Entry> {
        private Comparable key;
        private final Object val;

        public Entry(Comparable key, Object val) {
            this.key = key;
            this.val = val;
        }

        @Override
        public int compareTo(Entry o) {
            return this.key.compareTo(o.key);
        }
    }

    public void put(Key key, Object val) {
        if (key == null) {
            throw new IllegalArgumentException("key  is  null");
        }
        insert(root, key, val, height);
        n++;
    }

    /**
     * 通过key获取Value
     *
     * @param key key
     * @return
     */
    public Value get(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("key  is  null");
        }
        return search(root, key, height);
    }

    /**
     * 查询元素
     *
     * @param node
     * @param key
     * @param ht
     * @return
     */
    private Value search(Node node, Key key, int ht) {
        if (node == null) {
            return null;
        }
        for (int i = 0; i < node.m; i++) {
            if (eq(key, node.children[i].key)) {
                return (Value) node.children[i].val;
            } else if (less(key, node.children[i].key)) {
                return search(node.childrenNode[i], key, ht - 1);
            } else if (i + 1 == node.m) {
                return search(node.childrenNode[i + 1], key, ht - 1);
            }
        }
        return null;
    }

    //todo 移除Key
    public boolean remove(Key key, int ht) {

        return false;
    }

    /**
     * 插入节点
     *
     * @param node 节点
     * @param key  插入的Key
     * @param val  插入的Value
     * @param ht   高度
     */
    private void insert(Node node, Key key, Object val, int ht) {
        int j;

        Entry entry = new Entry(key, val);

        if (ht == 0) {
            node.addEntry(entry);
        } else {
            for (j = 0; j < node.m; j++) {
                if (less(key, node.children[j].key)) {
                    insert(node.childrenNode[j], key, val, ht - 1);
                    break;
                } else if (j + 1 == node.m) {
                    insert(node.childrenNode[j + 1], key, val, ht - 1);
                    break;
                }
            }
        }

        if (node.m >= M) {
            split(node);
        }
    }

    /**
     * 分裂
     *
     * @param node 需要分裂的节点
     * @return
     */
    private void split(Node node) {
        Node t = new Node();
        node.m = M / 2;
        for (int j = 1; j < M / 2; j++) {
            t.addEntry(node.children[M / 2 + j]);

        }

        for (int j = 1; j < M / 2 + 1; j++) {
            if (node.childrenNode[M / 2 + j] != null) {
                t.addChildrenNode(node.childrenNode[M / 2 + j]);
                node.childrenNode[M / 2 + j].parent = t;
                node.removeChildrenNode(M / 2 + j);
            }
        }


        Node parent;
        if ((parent = node.parent) == null) {
            parent = new Node();
            root = parent;
            height++;
            parent.addChildrenNode(node);
        }
        Entry entry = new Entry(node.children[M / 2].key, node.children[M / 2].val);
        parent.addEntry(entry);
        parent.addChildrenNode(t);
        if (parent.m >= M) {
            split(parent);
        }
    }

    public boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) <= 0;
    }

    private boolean eq(Comparable a, Comparable b) {
        return a.compareTo(b) == 0;
    }

    public String toString() {
        return toString(root, height, "") + "\n";
    }

    private String toString(Node h, int ht, String indent) {
        StringBuilder s = new StringBuilder();
        Entry[] children = h.children;

        if (h.nodeSize > 0) {
            for (int i = 0; i < h.nodeSize; i++) {
                s.append(toString(h.childrenNode[i], ht - 1, indent + "     "));
            }
        }
        for (int j = 0; j < h.m; j++) {
            s.append(indent + children[j].key + " " + children[j].val + "\n");
        }
        s.append("\n");
        return s.toString();
    }
}
