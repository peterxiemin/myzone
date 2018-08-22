package com.imgeek.algorithm;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class QuickSortTest extends SortTest {
    private MySort quickSort;
    private int size = 10000;
    private int[] arr = new int[size];

    @Before
    public void setUp() throws Exception {
        for (int i = 0; i < size; i++) {
            arr[i] = (int) (Math.random() * size);
        }
    }

    @Test
    public void sort() {
        quickSort = new QuickSort(MySort.Sort.DESC);
        quickSort.sort(arr);
        sortAssert(quickSort, size, arr);
        quickSort = new QuickSort(MySort.Sort.ASC);
        quickSort.sort(arr);
        sortAssert(quickSort, size, arr);
    }
}


