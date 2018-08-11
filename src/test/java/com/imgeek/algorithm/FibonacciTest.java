package com.imgeek.algorithm;

import org.junit.Test;

import static org.junit.Assert.*;

public class FibonacciTest {

    @Test
    public void fibonacciTest() {
        Fibonacci fibonacci = new Fibonacci();
        StringBuffer strbuf = new StringBuffer();
        for (int i = 1; i < 20; i++) {
            strbuf.append(fibonacci.fibonacci(i)).append(",");
        }
        System.out.println(strbuf.substring(0, strbuf.length() - 1));
    }
}