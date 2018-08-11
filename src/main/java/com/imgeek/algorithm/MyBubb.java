package com.imgeek.algorithm;

/**
 * auth:    xiemin
 * date:    2018-08-04
 * desc:    冒泡排序
 */
public class MyBubb {
    public void sort(int[] arr) {
        boolean fastTag = true;
        for (int i = 0; i < arr.length; i++) {
            if (!fastTag) break; //如果是有序，则不需要后面的判断
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j+1]) {
                    int tem = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = tem;
                    fastTag = true;
                }
            }
        }
    }

    public void showForeach(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.printf(String.valueOf(arr[i]).concat(" "));
        }
    }
}
