package com.imgeek.algorithm;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author :xiemin
 * @date: 2018-09-19
 */
public class MyListTest {
    private MyList myList;

    public MyListTest() {
        myList = new MyList();
    }

    @Before
    public void create() {
        int size = 10;
        for (int i = 0; i < size; i++) {
            myList.add(i);
        }
    }

    @Test
    public void add() {
        int size = 10;
        assertEquals(size, myList.size());
    }

    @Test
    public void contains() {
        assertEquals(true, myList.contains(1));
    }

    @Test
    public void remove() {
        myList.remove(9);
        assertEquals(false, myList.contains(9));

    }

    @Test
    public void get() {
        int num = 5;
        assertEquals(num, myList.get(5));
    }
}