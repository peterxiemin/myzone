package com.imgeek.algorithm;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SelectionSortTest extends SortTest {

    private MySort selectionSort;
    private int size = 1000;
    private int[] arr = new int[size];

    @Before
    public void setUp() throws Exception {
        for (int i = 0; i < size; i++) {
            arr[i] = (int) (Math.random() * size);
        }
    }

    @Test
    public void sort() {
        selectionSort = new SelectionSort(MySort.Sort.DESC);
        selectionSort.sort(arr);
        sortAssert(selectionSort, size, arr);
        selectionSort = new SelectionSort(MySort.Sort.ASC);
        selectionSort.sort(arr);
        sortAssert(selectionSort, size, arr);
    }
}