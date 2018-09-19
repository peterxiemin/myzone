package com.imgeek.algorithm;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author :xiemin
 * @date: 2018-09-19
 */
public class InsertionSortTest extends SortTest {

    private MySort insertionSort;
    private int size = 1000;
    private int[] arr = new int[size];

    @Before
    public void setUp() throws Exception {
        insertionSort = new InsertionSort(MySort.Sort.ASC);
        for (int i = 0; i < size; i++) {
            arr[i] = (int) (Math.random() * size);
        }
    }

    @Test
    public void sort() {
        insertionSort = new InsertionSort(MySort.Sort.DESC);
        insertionSort.sort(arr);
        sortAssert(insertionSort, size, arr);
        insertionSort = new InsertionSort(MySort.Sort.ASC);
        insertionSort.sort(arr);
        sortAssert(insertionSort, size, arr);
    }
}