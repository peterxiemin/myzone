package com.imgeek.algorithm;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class QuickSortTest {
    private QuickSort quickSort;
    private int size = 10000;
    private int[] arr = new int[size];

    @Before
    public void setUp() throws Exception {
        quickSort = new QuickSort(MySort.Sort.ASC);
        for (int i = 0; i < size; i++) {
            arr[i] = (int) (Math.random() * size);
        }
    }

    @Test
    public void sort() {
        quickSort.sort(arr);
        quickSort.showForeach(arr);
        for (int i = 0; i < size - 1; i++) {
            if (quickSort.getSort() == MySort.Sort.DESC) {
                if (arr[i] > arr[i + 1]) {
                    assertFalse(true);
                }
            } else {
                if (arr[i] < arr[i + 1]) {
                    assertFalse(true);
                }
            }
        }
    }
}