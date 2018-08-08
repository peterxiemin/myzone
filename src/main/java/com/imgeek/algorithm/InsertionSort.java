package com.imgeek.algorithm;

/**
 * 插入排序
 * 通过构建有序序列，对于未排序数据，在已排序序列中从后向前扫描，找到相应位置并插入
 * @author HongMong
 * @date 2018-08-08
 */
public class InsertionSort {

    public int[] sort(int[] ints){
        int length = ints.length;
        int preIndex,current;
        for(int i = 1;i < length;i++){
            preIndex = i - 1;
            current = ints[i];
            while (preIndex >= 0 && ints[preIndex] > current){
                ints[preIndex + 1] = ints[preIndex];
                preIndex --;
            }
            ints[preIndex + 1] = current;
        }
        return ints;
    }
}
