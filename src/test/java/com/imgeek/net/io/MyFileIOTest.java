package com.imgeek.net.io;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * @author: xiemin
 * @date: 2018/9/26 15:42
 */
@Slf4j
public class MyFileIOTest {
    @Test
    public void readLineTest() throws IOException {
        URL url = MyFileIOTest.class.getClassLoader().getResource("net/io/MyFileIOTest.txt");
        ArrayList list = new ArrayList();
        MyFileIO myFileIO = new MyFileIO(new File(url.getFile()), (str) -> {
            char[] dstChars = new char[1];
            str.getChars(0, 1, dstChars, 0);
            list.add(dstChars[0]);
        }, false);
        assertArrayEquals(new char[]{'a', 'b', 'c', 'd', 'e', 'f'}, ArrayToBytes(list));
        assertEquals(6, myFileIO.getLine());
    }

    @Test
    public void readFileByNIOTest() throws IOException {
        URL url = MyFileIOTest.class.getClassLoader().getResource("net/io/MyFileIOTest.txt");
        ArrayList list = new ArrayList();
        MyFileIO myFileIO = new MyFileIO(new File(url.getFile()), (str) -> {
            char[] dstChars = new char[1];
            str.getChars(0, 1, dstChars, 0);
            list.add(dstChars[0]);
        }, true);
        assertArrayEquals(new char[]{'a', 'b', 'c', 'd', 'e', 'f'}, ArrayToBytes(list));
        assertEquals(6, myFileIO.getLine());
    }

    @Test
    public void readFileByNIOTest2() throws IOException {
        URL url = MyFileIOTest.class.getClassLoader().getResource("net/io/MyFileIOTest2.txt");
        ArrayList list = new ArrayList();
        MyFileIO myFileIO = new MyFileIO(new File(url.getFile()), (str) -> {
            char[] dstChars = new char[1];
            str.getChars(0, 1, dstChars, 0);
            list.add(dstChars[0]);
        }, true);
        assertArrayEquals(new char[]{'a', 'b', 'c', 'd', 'e', 'f'}, ArrayToBytes(list));
        assertEquals(6, myFileIO.getLine());
    }

    /**
     * java String 对使用者屏蔽最后结束符
     */
    @Test
    public void StringTest() {
        String str = "xieminshitiancai";
        assertEquals(16, str.length());
        assertEquals("xiemin", new String(str.getBytes(), 0, 5 + 1));
    }

    private char[] ArrayToBytes(ArrayList list) {
        Object[] objects = list.toArray();
        char[] chars = new char[objects.length];
        for (int i = 0; i < objects.length; i++) {
            chars[i] = (char) objects[i];
        }
        return chars;
    }
}