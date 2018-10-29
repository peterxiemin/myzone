package com.imgeek.algorithm;

import org.junit.Before;
import org.junit.Test;

public class BTreeTest {
    private int size = 1000;
    private int[] arr = new int[size];

    @Before
    public void setUp() throws Exception {
        for (int i = 0; i < size; i++) {
            arr[i] = (int) (Math.random() * size);
        }
    }

    @Test
    public void test() {
        BTree<Integer, Integer> bTree = new BTree<>();
        for (int i = 0; i < arr.length; i++) {
            bTree.put(i, i);
        }
        System.out.println(bTree.toString());
    }
}