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
            Node node = new Node();
            node.data = i;
            myList.add(node);
        }
    }

    @Test
    public void add() {
        int size = 10;
        assertEquals(size, myList.size());
    }

    @Test
    public void contains() {
        Node node = new Node();
        node.data = 9;
        assertEquals(true, myList.contains(node));
    }

    @Test
    public void remove() {
        Node node = new Node();
        node.data = 9;
        node = myList.remove(node);
        assertEquals(false, myList.contains(node));

    }

    @Test
    public void get() {
        int num = 5;
        Node node = myList.get(5);
        assertEquals(num, node.data);
    }
}