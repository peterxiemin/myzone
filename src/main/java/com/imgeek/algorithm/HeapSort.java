package com.imgeek.algorithm;

/**
 * auth:    xiemin
 * date:    2018-08-05
 * desc:    堆排序
 */

public class HeapSort extends MySort {
    private MyHeap<Integer> myHeap;

    public HeapSort(Sort sort) {
        super(sort);
        if (sort == Sort.ASC) {
            myHeap = new MyHeap<>((o1, o2) -> o1.compareTo(o2) > 0 ? true : false);
        } else {
            myHeap = new MyHeap<>((o1, o2) -> o1.compareTo(o2) < 0 ? true : false);
        }
    }

    @Override
    public void sort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            myHeap.insertNode(arr[i]);
        }
        for (int i = 0; i < arr.length; i++) {
            arr[i] = myHeap.extract();
        }
    }
}
