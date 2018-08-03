package com.imgeek.algorithm;

public class Fibonacci {
    int fibonacci(int i) {
        if (i <= 2) {
            return 1;
        } else {
            return fibonacci(i - 1) + fibonacci(i - 2);
        }
    }
}
