package com.imgeek.algorithm;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * auth:    xiemin
 * date:    2018-09-17
 */

@Slf4j
public class FibonacciTest {

    @Test
    public void fibonacciTest() {
        Fibonacci fibonacci = new Fibonacci();
        StringBuffer strbuf = new StringBuffer();
        for (int i = 1; i < 20; i++) {
            strbuf.append(fibonacci.fibonacci(i)).append(",");
        }
        log.info(strbuf.substring(0, strbuf.length() - 1));
    }
}