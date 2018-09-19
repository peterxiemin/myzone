package com.imgeek.algorithm;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author :xiemin
 * @date: 2018-09-19
 */
public class CountingSortTest extends SortTest {

    private CountingSort countingSort;
    private int size = 10000;
    private int[] arr = new int[size];

    @Before
    public void setUp() throws Exception {
        for (int i = 0; i < size; i++) {
            arr[i] = (int) (Math.random() * size * 10);
        }
    }

    @Test
    public void sort() {
        countingSort = new CountingSort(MySort.Sort.DESC);
        countingSort.sort(arr);
        sortAssert(countingSort, size, arr);
        countingSort = new CountingSort(MySort.Sort.ASC);
        countingSort.sort(arr);
        sortAssert(countingSort, size, arr);
    }
}