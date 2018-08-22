package com.imgeek.algorithm;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MergeSortTest extends SortTest {

    private MySort mergeSort;
    private int size = 1000;
    private int[] arr = new int[size];

    @Before
    public void setUp() throws Exception {
        mergeSort = new MergeSort(MySort.Sort.ASC);
        for (int i = 0; i < size; i++) {
            arr[i] = (int) (Math.random() * size);
        }
    }

    @Test
    public void sort() {
        mergeSort = new MergeSort(MySort.Sort.DESC);
        mergeSort.sort(arr);
        sortAssert(mergeSort, size, arr);
        mergeSort = new MergeSort(MySort.Sort.ASC);
        mergeSort.sort(arr);
        sortAssert(mergeSort, size, arr);
    }
}