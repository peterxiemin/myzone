package com.imgeek.algorithm;

/**
 * auth : xiemin
 * date : 2018-08-04
 * desc : 实现斐波那契数列
 */

public class Fibonacci {
    int fibonacci(int i) {
        if (i <= 2) {
            return 1;
        } else {
            return fibonacci(i - 1) + fibonacci(i - 2);
        }
    }
}
