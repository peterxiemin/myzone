package com.imgeek.jvm;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author :xiemin
 * @date: 2018-09-19
 */
@Slf4j
public class ReturnFinallyTest {
    ReturnFinally returnFinally;

    @Before
    public void setUp() {
        returnFinally = new ReturnFinally();
    }

    @Test
    public void mustFinally3Test() {
        int ret = 0;
        try {
            ret = returnFinally.mustFinally3();
        } catch (Exception e) {
            assertEquals("/ by one", e.getMessage());
        }
        assertEquals(1, ret);
    }

    @Test
    public void mustFinally4Test() {
        try {
            int ret = returnFinally.mustFinally4();
            assertEquals(2, ret);
        } catch (Exception e) {
            assertEquals("/ by one", e.getMessage());
        }
    }

    @Test
    public void mustFinally5Test() {
        int ret = returnFinally.mustFinally5();
        assertEquals(1, ret);
    }

    @Test
    public void mustFinally6Test() {
        try {
            int ret = returnFinally.mustFinally6();
            assertEquals(1, ret);
        } catch (Exception e) {
            assertEquals("/ by zero", e.getMessage());
        }
    }

    @Test
    public void mustFinally7() {
        try {
            int ret = returnFinally.mustFinally7();
            assertEquals(1, ret);
        } catch (Exception e) {
            assertEquals("/ by one", e.getMessage());
        }
    }
}