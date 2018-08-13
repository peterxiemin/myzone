package com.imgeek.algorithm;

public abstract class MySort {

    private Sort sort;

    public MySort(Sort sort){
        this.sort = sort;
    }

    public Sort getSort(){
        return this.sort;
    }

    public abstract void sort(int[] ints);

    public boolean compare(int a,int b){
        return this.sort == Sort.ASC ? a > b : a < b;
    }

    public enum Sort{
        DESC,ASC
    }
}
