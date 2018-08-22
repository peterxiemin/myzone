package com.imgeek.algorithm;

import static org.junit.Assert.assertTrue;

/**
 * auth:    xiemin
 * date:    2018-08-21
 * desc:    排序测试基类
 */

public class SortTest {
    public void sortAssert(MySort mySort, int size, int[] arr) {
        for (int i = 0; i < size - 1; i++) {
            if (mySort.getSort() == MySort.Sort.DESC) {
                assertTrue(arr[i] >= arr[i + 1]);
            } else {
                assertTrue(arr[i] <= arr[i + 1]);
            }
        }
    }
}