package com.imgeek.util.json;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author :xiemin
 * @date: 2018-09-19
 */
class TestClass {
    Long id;
    Integer batchId;
    String datas;
    List list;
    HashMap map;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getBatchId() {
        return batchId;
    }

    public void setBatchId(Integer batchId) {
        this.batchId = batchId;
    }

    public String getDatas() {
        return datas;
    }

    public void setDatas(String datas) {
        this.datas = datas;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public HashMap getMap() {
        return map;
    }

    public void setMap(HashMap map) {
        this.map = map;
    }
}

public class IJsonUtilTest {
    @Test
    public void orgJsonEncodeLong() throws InstantiationException, IllegalAccessException {
        String str = "{\"id\":100000002668344,\"batchId\":56,\"datas\":\"xxxx\"}";
        OrgJson orgJson = new OrgJson();
        TestClass testClass = orgJson.encode(str, TestClass.class);
        assertEquals(testClass.id.longValue(), 100000002668344L);
    }

    @Test
    public void orgJsonEncodeInteger() throws InstantiationException, IllegalAccessException {
        String str = "{\"id\":100000002668344,\"batchId\":56,\"datas\":\"xxxx\"}";
        OrgJson orgJson = new OrgJson();
        TestClass testClass = orgJson.encode(str, TestClass.class);
        assertEquals(testClass.batchId.intValue(), 56);
    }

    @Test
    public void orgJsonEncodeString() throws InstantiationException, IllegalAccessException {
        String str = "{\"id\":100000002668344,\"batchId\":56,\"datas\":\"xxxx\"}";
        OrgJson orgJson = new OrgJson();
        TestClass testClass = orgJson.encode(str, TestClass.class);
        assertEquals(testClass.datas, "xxxx");
    }

    @Test
    public void orgJsonEncodeList() throws InstantiationException, IllegalAccessException {
        String str =
                "{\n" +
                        "\t\"id\": 100000002668344,\n" +
                        "\t\"batchId\": 56,\n" +
                        "\t\"datas\": \"xxxx\",\n" +
                        "\t\"list\":\t[1, 2, 3, 4]\n" +
                        "}";
        OrgJson orgJson = new OrgJson();
        TestClass testClass = orgJson.encode(str, TestClass.class);
        assertEquals(testClass.list.get(0), 1);
    }

    @Test
    public void orgJsonEncodeMap() throws InstantiationException, IllegalAccessException {
        String str =
                "{\n" +
                        "\t\"id\": 100000002668344,\n" +
                        "\t\"batchId\": 56,\n" +
                        "\t\"datas\": \"xxxx\",\n" +
                        "\t\"list\":\t[1, 2, 3, 4],\n" +
                        "\t\"map\": {\"a\":3.1, \"b\":\"123\"}\n" +
                        "}";
        OrgJson orgJson = new OrgJson();
        TestClass testClass = orgJson.encode(str, TestClass.class);
        assertEquals(testClass.map.get("a"), 3.1);
        assertEquals(testClass.map.get("b"), "123");
    }

    @Test
    public void GJsonEncodeLong() throws InstantiationException, IllegalAccessException {
        String str = "{\"id\":100000002668344,\"batchId\":56,\"datas\":\"xxxx\"}";
        GJson gJson = new GJson();
        TestClass testClass = gJson.encode(str, TestClass.class);
        assertEquals(testClass.id.longValue(), 100000002668344L);
    }

    @Test
    public void GJsonEncodeInteger() throws InstantiationException, IllegalAccessException {
        String str = "{\"id\":100000002668344,\"batchId\":56,\"datas\":\"xxxx\"}";
        GJson gJson = new GJson();
        TestClass testClass = gJson.encode(str, TestClass.class);
        assertEquals(testClass.batchId.intValue(), 56);
    }

    @Test
    public void GJsonEncodeString() throws InstantiationException, IllegalAccessException {
        String str = "{\"id\":100000002668344,\"batchId\":56,\"datas\":\"xxxx\"}";
        GJson gJson = new GJson();
        TestClass testClass = gJson.encode(str, TestClass.class);
        assertEquals(testClass.datas, "xxxx");
    }

    /**
     * gson内部会将number类型转化为double表示，需要特别注意
     *
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    @Test
    public void GJsonEncodeList() throws InstantiationException, IllegalAccessException {
        String str =
                "{\n" +
                        "\t\"id\": 100000002668344,\n" +
                        "\t\"batchId\": 56,\n" +
                        "\t\"datas\": \"xxxx\",\n" +
                        "\t\"list\":\t[1, 2, 3, 4]\n" +
                        "}";
        GJson gJson = new GJson();
        TestClass testClass = gJson.encode(str, TestClass.class);
        assertEquals(testClass.list.get(0), 1.0);
    }

    @Test
    public void GJsonEncodeMap() throws InstantiationException, IllegalAccessException {
        String str =
                "{\n" +
                        "\t\"id\": 100000002668344,\n" +
                        "\t\"batchId\": 56,\n" +
                        "\t\"datas\": \"xxxx\",\n" +
                        "\t\"list\":\t[1, 2, 3, 4],\n" +
                        "\t\"map\": {\"a\":3.1, \"b\":\"123\"}\n" +
                        "}";
        GJson gJson = new GJson();
        TestClass testClass = gJson.encode(str, TestClass.class);
        assertEquals(testClass.map.get("a"), 3.1);
        assertEquals(testClass.map.get("b"), "123");
    }

    @Test
    public void FastEncodeLong() throws InstantiationException, IllegalAccessException {
        String str = "{\"id\":100000002668344,\"batchId\":56,\"datas\":\"xxxx\"}";
        FastJson fastJson = new FastJson();
        TestClass testClass = fastJson.encode(str, TestClass.class);
        assertEquals(testClass.id.longValue(), 100000002668344L);
    }

    @Test
    public void FastEncodeInteger() throws InstantiationException, IllegalAccessException {
        String str = "{\"id\":100000002668344,\"batchId\":56,\"datas\":\"xxxx\"}";
        FastJson fastJson = new FastJson();
        TestClass testClass = fastJson.encode(str, TestClass.class);
        assertEquals(testClass.batchId.intValue(), 56);
    }

    /**
     * Fastjson 需要类定义增加get和set方法，否则encode会失效
     *
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    @Test
    public void FastEncodeString() throws InstantiationException, IllegalAccessException {
        String str = "{\"id\":100000002668344,\"batchId\":56,\"datas\":\"xxxx\"}";
        FastJson fastJson = new FastJson();
        TestClass testClass = fastJson.encode(str, TestClass.class);
        assertEquals(testClass.datas, "xxxx");
    }

    /**
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    @Test
    public void FastEncodeList() throws InstantiationException, IllegalAccessException {
        String str =
                "{\n" +
                        "\t\"id\": 100000002668344,\n" +
                        "\t\"batchId\": 56,\n" +
                        "\t\"datas\": \"xxxx\",\n" +
                        "\t\"list\":\t[1, 2, 3, 4]\n" +
                        "}";
        FastJson fastJson = new FastJson();
        TestClass testClass = fastJson.encode(str, TestClass.class);
        assertEquals(testClass.list.get(0), 1);
    }

    /**
     * fastjson内部会将浮点型数字类型使用Bigdecimal表示
     *
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    @Test
    public void FastEncodeMap() throws InstantiationException, IllegalAccessException {
        String str =
                "{\n" +
                        "\t\"id\": 100000002668344,\n" +
                        "\t\"batchId\": 56,\n" +
                        "\t\"datas\": \"xxxx\",\n" +
                        "\t\"list\":\t[1, 2, 3, 4],\n" +
                        "\t\"map\": {\"a\":3.1, \"b\":\"123\"}\n" +
                        "}";
        FastJson fastJson = new FastJson();
        TestClass testClass = fastJson.encode(str, TestClass.class);
        assertEquals(testClass.map.get("a"), new BigDecimal("3.1"));
        assertEquals(testClass.map.get("b"), "123");
    }
}