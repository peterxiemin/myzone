package com.imgeek.algorithm;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@Slf4j
public class MyHeapTest {
    MyHeap<Integer> myHeap;
    private static int SIZE = 100;
    private static int ZERO = 0;
    MyComparable<Integer> myComparable;

    @Before
    public void setup() {
        myComparable = (o1, o2) -> o1.compareTo(o2) > 0 ? true : false;
        myHeap = new MyHeap<>(myComparable);
        for (int i = 0; i < SIZE; i++) {
            int data = (int) (Math.random() * SIZE * 10);
            myHeap.insertNode(data);
        }
        log.debug(myHeap.toString());
    }

    @Test
    public void childVaildHeapTest() {
        int rootIndex = myHeap.heapRootIndex();
        assertEquals(ZERO, rootIndex);
        recursionTraverseHeap(rootIndex, myComparable);
    }

    @Test
    public void extractTest() {
        List<Integer> list = new ArrayList<>();
        while (myHeap.size() != 0) {
            list.add(myHeap.extract());
        }
        for (int i = 0; i < list.size() - 1; i++) {
            log.debug("l1: ".concat(String.valueOf(list.get(i))).concat(" l2: ").concat(String.valueOf(list.get(i+1))));
            assertFalse(list.get(i) > list.get(i+1));
        }
    }

    private void recursionTraverseHeap(int parentIndex, MyComparable<Integer> myComparable) {
        int childLeftIndex = myHeap.childLeftIndex(parentIndex);
        if (childLeftIndex == -1) return;
        log.debug("parentIndex:"
                .concat(String.valueOf(parentIndex))
                .concat(" parentNode:")
                .concat(String.valueOf(myHeap.parent(parentIndex)))
                .concat(" childLeftIndex:")
                .concat(String.valueOf(childLeftIndex))
                .concat(" childLeftNode:")
                .concat(String.valueOf(myHeap.childLeft(childLeftIndex))
                ));
        boolean ret = myComparable.compare(myHeap.get(parentIndex), myHeap.get(childLeftIndex));
        assertFalse(ret);
        recursionTraverseHeap(childLeftIndex, myComparable);
        int childRightIndex = myHeap.childRightIndex(parentIndex);
        if (childRightIndex == -1) return;

        log.debug("parentIndex:"
                .concat(String.valueOf(parentIndex))
                .concat(" parentNode:")
                .concat(String.valueOf(myHeap.parent(parentIndex)))
                .concat(" childRightIndex:")
                .concat(String.valueOf(childRightIndex))
                .concat(" childRight:")
                .concat(String.valueOf(myHeap.childRight(childRightIndex))
                ));
        assertFalse(myComparable.compare(myHeap.get(parentIndex), myHeap.get(childRightIndex)));
        recursionTraverseHeap(childRightIndex, myComparable);
    }
}