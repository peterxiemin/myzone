package com.imgeek.algorithm;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author :xiemin
 * @date: 2018-09-19
 */
public class MyIntegerTest {

    @Test
    public void parseInt() {
        try {
            MyInteger.parseInt("123456a");
        } catch (Exception e) {
            assertTrue(e.getMessage().contains("invaild char"));
        }
    }

}