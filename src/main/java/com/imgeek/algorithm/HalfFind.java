package com.imgeek.algorithm;

/**
 * auth:    xiemin
 * date:    2018-08-05
 * desc:    二分查找
 */

public class HalfFind {

    private int sorted = 0;

    public HalfFind(int sorted) {
        sorted = 0; //sorted ：0 正序 sorted ：1 逆序
    }

    public int find(int[] arr, int key) {
       return find(arr, key, 0, arr.length - 1);
    }

    /**
     * @param arr
     * @param key
     * @param start
     * @param end
     * @return
     */
    private int find(int[] arr, int key, int start, int end) {
        /**
         * 判断是否是有序数组
         */
        if (!isSorted(arr)) {
            throw new NumberFormatException("not sorted array");
        }

        int half = (start + end) / 2;

        if ((start + 1) == end) {
            if (arr[start] == key) {
                return start;
            } else if (arr[end] == key) {
                return end;
            } else {
                return -1;
            }
        }

        if (sorted == 0) { //正序
            if (key > arr[half])
                return find(arr, key, half, end);
            else if (key < arr[half])
                return find(arr, key, start, half);
        }
        if (sorted == 1) { //逆序
            if (key < arr[half])
                return find(arr, key, half, end);
            else if (key > arr[half])
                return find(arr, key, start, half);
        }
        return half;
    }

    /**
     * 判断数组是否有序 sorted = 0: 正序 sorted = 1: 逆序
     *
     * @param arr
     * @return
     */
    private boolean isSorted(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (sorted == 1 && arr[i] < arr[i + 1]) {
                return false;
            }
            if (sorted == 0 && arr[i] > arr[i + 1]) {
                return false;
            }
        }
        return true;
    }
}
