package com.imgeek.algorithm;

/**
 * 计数排序
 *
 * @author HongMong
 * @date 2018-09-12
 */
public class CountingSort extends MySort{

    public CountingSort(Sort sort) {
        super(sort);
    }

    @Override
    public void sort(int[] arr) {
        if(arr == null || arr.length < 1){
            return;
        }
        //获取数组最大值
        int max = 0;
        for(int i : arr){
            if(i > max){
                max = i;
            }
        }

        int[] tempArr = new int[max + 1];

        for(int i : arr){
            tempArr[arr[i]]++;
        }


        if(this.getSort().equals(Sort.DESC)){
            int index = arr.length - 1;
            for(int i = 0;i < tempArr.length;i++){
                while (tempArr[i] > 0){
                    arr[index--] = i;
                    tempArr[i]--;
                }
            }
        }else {
            int index = 0;
            for (int i = 0; i < tempArr.length; i++) {
                while (tempArr[i] > 0) {
                    arr[index++] = i;
                    tempArr[i]--;
                }
            }
        }
    }
}
