package com.imgeek.algorithm;

import java.lang.reflect.Array;

/**
 * 归并排序
 * @author HongMong
 * @date 2018-08-08
 */
public class MergeSort extends MySort{

    public MergeSort(Sort sort) {
        super(sort);
    }

    public void sort(int[] ints){
        mergeSort(ints,0,ints.length-1);
    }

    private void mergeSort(int[] ints,int low,int high){
        int middle = (low + high) / 2;
        if(low < high){

           mergeSort(ints,low ,middle);

           mergeSort(ints,middle + 1,high);

           merge(ints,low,middle,high);
        }
    }

    private void merge(int[] ints,int low,int middle,int high){
        int[] temp = new int[high - low + 1];
        int _left = low;
        int _right = middle + 1;
        int index = 0;
        while (_left <= middle && _right <= high){

            if(compare(ints[_left],ints[_right])){
                temp[index++] = ints[_right++];
            }else {
                temp[index++] = ints[_left++];
            }
        }
        while (_left <= middle){
            temp[index++] = ints[_left++];
        }

        while (_right <= high){
            temp[index++] = ints[_right++];
        }

        for(int i : temp){
            ints[low++] = i;
        }
    }
}
