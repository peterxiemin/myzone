package com.imgeek.algorithm;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class MyBubbTest {

    @Test
    public void sort() {
        int size = 1000;
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            int random = (int) (Math.random() * size);
            arr[i] = random;
        }

        MyBubb myBubb = new MyBubb();
        myBubb.sort(arr);
        myBubb.showForeach(arr);
    }
}