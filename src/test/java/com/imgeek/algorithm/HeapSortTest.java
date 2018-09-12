package com.imgeek.algorithm;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class HeapSortTest extends SortTest {
    private MySort mySort;
    private static int size = 1000;
    private int[] arr = new int[size];
    @Before
    public void setUp() throws Exception {
        for (int i = 0; i < size; i++) {
            arr[i] = (int) (Math.random() * size);
        }
    }

    @Test
    public void sort() {
        mySort = new HeapSort(MySort.Sort.DESC);
        mySort.sort(arr);
        sortAssert(mySort, size, arr);
        mySort = new HeapSort(MySort.Sort.ASC);
        mySort.sort(arr);
        sortAssert(mySort, size, arr);
    }
}