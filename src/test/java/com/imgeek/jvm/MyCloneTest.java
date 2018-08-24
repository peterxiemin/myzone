package com.imgeek.jvm;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class MyCloneTest {
    private final static Logger log = LoggerFactory.getLogger(MyCloneTest.class);
    @Before
    public void setUp() throws Exception {
    }

    /**
     * 8种基本类型int/float/byte/boolean/double/char/short/long
     * clone函数会进行值复制
     */
    @Test
    public void cloneIntTest() {
        int i = 3;
        Thing thing = new Thing();
        Thing cloneThing = thing.clone();
        thing.setI(i);
        assertEquals(i, thing.getI());
        assertNotEquals(i, cloneThing.getI());
    }

    /**
     * String 类型比较特殊,虽然是对象，但是jvm当做基本类型，对其进行了值拷贝
     */
    @Test
    public void cloneStringTest() {
        String str = "hello world";
        Thing thing = new Thing();
        Thing cloneThing = thing.clone();
        thing.setStr(str);
        assertEquals(str, thing.getStr());
        assertNotEquals(str, cloneThing.getStr());
    }

    /**
     * ArrayList数组,jvm的clone函数只进行引用复制
     */
    @Test
    public void cloneArrayListTest() {
        List<String> arrayList = new ArrayList<>();
        Thing thing = new Thing();
        arrayList.add("");
        thing.setArrayList(arrayList);

        Thing cloneThing = thing.clone();
        assertEquals(1, thing.getArrayList().size());
        assertEquals(1, cloneThing.getArrayList().size());
    }

    /**
     * 自定义对象,jvm的clone函数只进行引用复制
     */
    @Test
    public void cloneObjTest() {
        int a = 3;
        DepObj depObj = new DepObj();
        Thing thing = new Thing();
        thing.setDepObj(depObj);
        Thing cloneThing  = thing.clone();
        depObj.setA(a);
        assertEquals(a, thing.getDepObj().getA());
        assertEquals(a, cloneThing.getDepObj().getA());
    }
}