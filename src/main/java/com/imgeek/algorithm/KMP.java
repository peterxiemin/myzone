package com.imgeek.algorithm;

/**
 * @author: xiemin
 * @date: 2018/11/18 9:30
 */
public class KMP {
    public boolean kmpSearch(char[] arr, char[] keys) {
        int[] keyArr = getKeyArr(keys);
        int i = 0;
        while (i < arr.length) {
            int m = i, n = 0;
            for (; n < keys.length; m++, n++) {
                if (arr[m] != keys[n]) {
                    break;
                }
            }
            if (n == keys.length) {
                return true;
            } else {
                i += keyArr[n];
            }
        }
        return false;
    }

    private int[] getKeyArr(char[] arr) {
        int[] retArr = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            if (i == 0 || i == 1) {
                retArr[i] = 1;
                continue;
            }
            retArr[i] = countSameBetweenPreAndSuff(copyArray(arr, 0, i - 1));
        }
        return retArr;
    }

    private char[] copyArray(char[] src, int start, int end) {
        int size = end - start + 1;
        char[] dst = new char[size];
        for (int i = 0; i < size; i++) {
            dst[i] = src[i + start];
        }
        return dst;
    }

    private int countSameBetweenPreAndSuff(char[] arr) {
        int p = 0, end = arr.length - 1, q = end, count = 0;
        while (p < end || q > 0) {
            char[] arr1 = copyArray(arr, 0, p);
            char[] arr2 = copyArray(arr, q, end);
            if (compareTwoArr(arr1, arr2)) count++;
            p++;
            q--;
        }
        return count == 0 ? count : count + 1;
    }

    private boolean compareTwoArr(char[] arr1, char[] arr2) {
        if (arr1 == null || arr2 == null) {
            return false;
        }

        if (arr1.length == 0 || arr2.length == 0) {
            return false;
        }


        if (arr1.length != arr2.length) {
            return false;
        }

        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) return false;
        }
        return true;
    }
}
