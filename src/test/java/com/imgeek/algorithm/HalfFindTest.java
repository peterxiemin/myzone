package com.imgeek.algorithm;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author :xiemin
 * @date: 2018-09-19
 */
public class HalfFindTest {

    private int size = 10;
    private int[] arr = new int[size];
    HalfFind halfFind;
    private int sorted = 0;

    @Before
    public void setUp() throws Exception {
        halfFind = new HalfFind(sorted);
    }

    @Test
    public void find1() {
        int[] arr = new int[]{1, 2, 2, 2, 2, 3, 4, 4, 6, 8};
        assertEquals(0, halfFind.find(arr, 1));
    }

    @Test
    public void find2() {
        int[] arr = new int[]{0, 2, 3, 4, 5, 6, 6, 6, 8, 9};
        assertEquals(9, halfFind.find(arr, 9));
    }

    @Test
    public void find3() {
        int[] arr = new int[]{0, 1, 2, 6, 7, 7, 7, 8, 9, 9};
        assertEquals(3, halfFind.find(arr, 6));
    }

    @Test
    public void find4() {
        int[] arr = new int[]{0, 1, 2, 6, 7, 7, 7, 8, 9, 9};
        assertEquals(-1, halfFind.find(arr, -1));
    }

    @Test
    public void find5() {
        int[] arr = new int[]{0, 1, 2, 6, 7, 7, 7, 8, 9, 9};
        assertEquals(-1, halfFind.find(arr, 10));
    }

    @Test
    public void find6() {
        int[] arr = new int[]{0, 1, 2, 6, 7, 7, 8, 10, 9, 9};
        try {
            halfFind.find(arr, 8);
        } catch (NumberFormatException e) {
            assertEquals("not sorted array", e.getMessage());
        }
    }
}