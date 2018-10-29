package com.imgeek.algorithm;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author: xiemin
 * @date: 2018/10/29 15:03
 */
public class MySkipListTest {
    private int max = 10;
    MySkipList<Integer> mySkipList = null;
    @Before
    public void setUp() {
        mySkipList = new MySkipList<>();

    }

    @Test
    public void mySkipListAddTest() {
        for (int i = 0; i < max; i++) {
            mySkipList.add((int) (Math.random() * max));
        }
        mySkipList.traverse();
    }
}