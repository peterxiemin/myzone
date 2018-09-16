package com.imgeek.algorithm;


/**
 * auth:    wangyufeng
 * date:    2018-09-15
 * desc:    快速排序-算法导论
 */
public class QuickSort2 extends MySort{
    public QuickSort2(Sort sort) {
        super(sort);
    }

    @Override
    public void sort(int[] arr) {
        sort(arr,0,arr.length - 1);
    }


    private void sort(int[] arr, int start, int end) {
        int i = start - 1;
        int j = start;
        int cur = arr[end];
        int temp;

        while(j < end){
            temp = arr[j];
            if(!compare(temp, cur)){
                i ++;
                arr[j] = arr[i];
                arr[i] = temp;
            }
            j++;
        }
        arr[end] = arr[++i];
        arr[i] = cur;
        if(start < i) {
            sort(arr, start, i - 1);
        }
        if(i < end) {
            sort(arr, i + 1, end);
        }
    }
}
