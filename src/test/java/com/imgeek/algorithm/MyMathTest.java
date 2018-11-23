package com.imgeek.algorithm;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author: xiemin
 * @date: 2018/11/23 9:51
 */
@Slf4j
public class MyMathTest {
    @Before
    public void setUp() {

    }
    @Test
    public void pow() {
        assertTrue((MyMath.pow(2, 32) - 1) > Integer.MAX_VALUE);
    }
}