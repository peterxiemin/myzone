package com.imgeek.algorithm;

/**
 * auth:    xiemin
 * date:    2018-08-05
 * desc:    最小堆
 */

public class MyHeap<T> {
    private T[] heap;

    public MyHeap(T[] arr) {
        this.heap = arr;
    }

    private T heapRoot() {
        return heap[heapRootIndex()];
    }

    private int heapRootIndex() {
        return size() > 0 ? 0 : -1;
    }

    private int size() {
        if (heap != null && heap.length > 0) {
            return heap.length;
        }
        throw new RuntimeException("heap is null or heap size is zero");
    }

    private T childLeft(int index) {
        int leftIndex = childLeftIndex(index);
        return leftIndex <= size() ? heap[leftIndex] : null;
    }

    private T childRight(int index) {
        int rightIndex = childRightIndex(index);
        return rightIndex <= size() ? heap[rightIndex] : null;
    }

    private int childLeftIndex(int index) {
        return index * 2 + 1;
    }

    private int childRightIndex(int index) {
        return index * 2 + 2;
    }

    private T parent(int index) {
        int parentIndex = (index - 1) / 2;
        return parentIndex > 0 && parentIndex <= size() ? heap[parentIndex] : null;
    }

    private void exchangeBetweenParentAndChild(int parentIndex, int childIndex) {
        if (((parentIndex * 2) + 1) != childIndex && ((parentIndex * 2) + 2) != childIndex) {
            throw new RuntimeException("the releation between parent and child is invaild");
        }
        if (parentIndex > size() || childIndex > size()) {
            throw new RuntimeException("index over limit");
        }
        T tem = heap[parentIndex];
        heap[parentIndex] = heap[childIndex];
        heap[childIndex] = tem;
    }

    public T[] buildHeapFromArray(int[] arr) {
        return null;
    }

    /**
     * insert data
     * @param data
     */
    private void insertNode(T data) {

    }
}
