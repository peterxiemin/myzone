package com.imgeek.jvm;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.*;

public class MyExtendsTest {
    private final static Logger log = LoggerFactory.getLogger(MyExtendsTest.class);

    @Before
    public void setUp() throws Exception {
    }

    /**
     * 如果子类和父类有相同（名称和类型）的变量，子类除了使用super方法外，无法访问父类的变量
     */
    @Test
    public void testExtendsFeature() {
        String str = "hello world";
        Parent parent = new Child(str);
        assertEquals(str, ((Child) parent).getParentStr());
        assertNull(parent.getStr());
    }
}