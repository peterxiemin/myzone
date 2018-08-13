package com.imgeek.algorithm;

import org.junit.Before;
import org.junit.Test;

import javax.sound.midi.Soundbank;

import static org.junit.Assert.*;

public class SelectionSortTest {

    private SelectionSort selectionSort;
    private int size = 66;
    private int[] arr = new int[size];

    @Before
    public void setUp() throws Exception {
        selectionSort = new SelectionSort(MySort.Sort.ASC);
        for (int i = 0; i < size; i++) {
            arr[i] = (int) (Math.random() * size);
        }
    }

    @Test
    public void sort() {
        selectionSort.sort(arr);
        for(int number : arr){
            System.out.print(number + "|");
        }
    }
}