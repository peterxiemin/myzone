package com.imgeek.jvm;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * auth:    xiemin
 * date:    2018-09-17
 */

public class MyInnerClassTest {

    MyInnerClass myInnerClass;
    @Before
    public void setUp() throws Exception {
        myInnerClass = new MyInnerClass();
    }

    @Test
    public void innerClass1Test() {
        assertEquals(1, myInnerClass.var);
        myInnerClass.add1();
        assertEquals(2, myInnerClass.var);
    }

    @Test
    public void innerClass2Test() {
        assertEquals(1, myInnerClass.var);
        myInnerClass.add2();
        assertEquals(1, myInnerClass.var);
    }

    @Test
    public void innerClass3Test() {
        assertEquals(1, myInnerClass.var);
        myInnerClass.add3();
        assertEquals(11, myInnerClass.var);
    }
}