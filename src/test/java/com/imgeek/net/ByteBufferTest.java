package com.imgeek.net;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import java.nio.ByteBuffer;

import static org.junit.Assert.*;

@Slf4j
public class ByteBufferTest {
    private int capacity;
    private ByteBuffer byteBuffer;
    private String strTest;
    @Before
    public void setUp() throws Exception {
        capacity = 1024;
        strTest = "xieminshitiancai";
        byteBuffer = ByteBuffer.allocate(capacity);
    }

    @Test
    public void byteBufferArrayTest() {
        byteBuffer.put("xieminshitiancai".getBytes());
        byteBuffer.flip();
        byte[] bytes = byteBuffer.array();
        assertEquals(1024, bytes.length);
    }

    @Test
    public void ASCIIDiffEFAndZERO() {
        char ef_c = '\n';
        char zero_c = 0;
        assertNotEquals(ef_c, zero_c);

        byte ef_b = '\n';
        byte zero_b = 0;
        assertNotEquals(ef_b, zero_b);
    }

    @Test
    public void byteBufferFlipTest() {
        assertEquals(0, byteBuffer.position());
        assertEquals(1024, byteBuffer.limit());
        assertEquals(1024, byteBuffer.capacity());
        byteBuffer.put("xieminshitiancai".getBytes());
        assertEquals(16, byteBuffer.position());
        assertEquals(1024, byteBuffer.limit());
        assertEquals(1024, byteBuffer.capacity());
        byteBuffer.flip();
        assertEquals(0, byteBuffer.position());
        assertEquals(16, byteBuffer.limit());
        assertEquals(1024, byteBuffer.capacity());
    }
}