package com.imgeek.algorithm;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author: xiemin
 * @date: 2018/11/19 11:31
 */
public class KMPTest {
    private KMP kmp;

    @Before
    public void setUp() throws Exception {
        kmp = new KMP();
    }

    @Test
    public void kmpSearch1() {
        String str = "asddefef";
        String key = "ddf";
        boolean ret = kmp.kmpSearch(str.toCharArray(), key.toCharArray());
        assertFalse(ret);
    }

    @Test
    public void kmpSearch2() {
        String str = "asddefef";
        String key = "dde";
        boolean ret = kmp.kmpSearch(str.toCharArray(), key.toCharArray());
        assertTrue(ret);
    }
}