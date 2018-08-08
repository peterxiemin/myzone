package com.imgeek.algorithm;

/**
 * 选择排序
 * 首先在未排序序列中找到最小（大）元素，存放到排序序列的起始位置，
 * 然后，再从剩余未排序元素中继续寻找最小（大）元素，
 * 然后放到已排序序列的末尾。以此类推，直到所有元素均排序完毕
 * @author HongMong
 * @date 2018-08-08
 */
public class SelectionSort {

    public int[] sort(int[] ints){
        int length = ints.length;
        int minIndex,temp;

        for(int i = 0;i < length - 1;i++){
            minIndex = i;
            for(int j = i + 1;j < length;j++){
                if(ints[minIndex] > ints[j]){
                    minIndex = j;
                }
            }
            temp = ints[i];
            ints[i] = ints[minIndex];
            ints[minIndex] = temp;
        }

        return ints;
    }
}
