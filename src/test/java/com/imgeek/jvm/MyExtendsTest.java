package com.imgeek.jvm;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author :xiemin
 * @date: 2018-09-19
 */
@Slf4j
public class MyExtendsTest {

    @Before
    public void setUp() throws Exception {
    }

    /**
     * 如果子类和父类有相同（名称和类型）的变量，子类除了使用super或者父类方法外，无法访问父类的变量
     */
    @Test
    public void testExtendsFeature() {
        Parent parent = new Child();
        Child child = new Child();
        /**
         * 通过super方法访问父类变量
         */
        assertEquals("parent", ((Child) parent).getParentStr());
        /**
         * 通过父类方法访问父类变量
         */
        assertEquals("parent", parent.getSelfStr());
        assertEquals("parent", child.getSelfStr());

        /**
         * 只能访问子类变量
         */
        assertEquals("child", parent.getStr());


    }
}