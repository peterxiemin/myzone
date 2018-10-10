package com.imgeek.net.io;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.*;
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

    /**
     * 检查BufferedReader是否每次读取最新文件内容
     */
    @Test
    public void checkFileUpdateTest() {
        URL url = MyFileIOTest.class.getClassLoader().getResource("net/io/MyFileCheckUpdate.txt");
        assertEquals("1", readFileByLines(url.getFile()));
        writeFileByLines(url.getFile(), "2");
        assertEquals("2", readFileByLines(url.getFile()));
        //rollback
        writeFileByLines(url.getFile(), "1");
        assertEquals("1", readFileByLines(url.getFile()));

    }

    public static String readFileByLines(String fileName) {
        File file = new File(fileName);
        BufferedReader reader = null;
        StringBuffer result = new StringBuffer();
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                // 显示行号
                result.append(tempString);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
            return result.toString();
        }
    }

    public static String writeFileByLines(String fileName, String content) {
        File file = new File(fileName);
        BufferedWriter writer = null;
        StringBuffer result = new StringBuffer();
        try {
            writer = new BufferedWriter(new FileWriter(file));
            // 一次读入一行，直到读入null为文件结束
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e1) {
                }
            }
            return result.toString();
        }
    }
}