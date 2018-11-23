package com.imgeek.algorithm;

/**
 * @author: xiemin
 * @date: 2018/11/23 9:50
 */
public class MyMath {
    public static long pow(int a, int b) {
        if (b == 0) {
            return 1;
        }
        if (b < 33 && b >= 1) {
            long ret = a;
            for (int i = 1; i < b; i++) {
                ret *= a;
            }
            return ret;
        }
        throw new NumberFormatException("b must lt 32");
    }
}
