package com.imgeek.algorithm;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class InsertionSortTest {

    private InsertionSort insertionSort;
    private int size = 66;
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

        insertionSort.sort(arr);
        for(int number : arr){
            System.out.print(number + "|");
        }
    }
}