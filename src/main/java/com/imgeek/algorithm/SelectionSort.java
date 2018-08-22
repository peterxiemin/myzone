package com.imgeek.algorithm;

/**
 * 选择排序
 * 首先在未排序序列中找到最小（大）元素，存放到排序序列的起始位置，
 * 然后，再从剩余未排序元素中继续寻找最小（大）元素，
 * 然后放到已排序序列的末尾。以此类推，直到所有元素均排序完毕
 * @author HongMong
 * @date 2018-08-08
 */
public class SelectionSort extends MySort{

    public SelectionSort(Sort sort) {
        super(sort);
    }

    public void sort(int[] arr){
        int length = arr.length;
        int minIndex,temp;

        for(int i = 0;i < length - 1;i++){
            minIndex = i;
            for(int j = i + 1;j < length;j++){
                if(compare(arr[minIndex],arr[j])){
                    minIndex = j;
                }
            }
            temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
        }
    }
}
