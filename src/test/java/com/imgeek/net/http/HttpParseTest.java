package com.imgeek.net.http;

import com.imgeek.net.http.constants.HttpHeader;
import com.imgeek.net.http.constants.HttpMethod;
import com.imgeek.net.http.constants.HttpVersion;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import static com.imgeek.net.http.constants.HttpConstants.*;
import static org.junit.Assert.*;

/**
 * @author: peterxiemin
 * @date: 2018/10/11 7:47
 * @desc:
 **/

@Slf4j
public class HttpParseTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void deParseTest() {
        HttpResponse httpResponse = new HttpResponse();
        httpResponse.setHttpVersion(HttpVersion.v_1_1);
        httpResponse.setStatus(200);
        httpResponse.setContent("hello world");
        HttpHeader httpHeader = new HttpHeader();
        httpHeader.setAccept(ACCEPT_VAL);
        httpResponse.setHttpHeader(httpHeader);
        String result = HttpParse.deParse(httpResponse);
        log.info(result);
        assertTrue(result.contains("Accept"));
        assertTrue(result.contains("hello world"));
        assertTrue(result.contains("200"));
        assertTrue(result.contains("OK"));
    }

    @Test
    public void parseTest() {
        String request = "GET /index.html HTTP/1.1\r\n" +
                "Accept: */*\r\n" +
                "Cache-Control: no-cache\r\n" +
                "Host: localhost:5763\r\n" +
                "Connection: Keep-Alive\r\n" +
                "User-Agent: Apache-HttpClient/4.5.5 (Java/1.8.0_152-release)\r\n" +
                "Accept-Encoding: gzip,deflate\r\n\r\n";
        HttpRequest httpRequest = HttpParse.parse(request);
        HttpHeader httpHeader = httpRequest.getHttpHeader();
        String httpVersion = null;
        switch (httpRequest.getHttpVersion()) {
            case v_1_1:
                httpVersion = VERSION_1_1;
                break;
            case v_1_0:
                httpVersion = VERSION_1_0;
                break;
        }
        assertEquals(VERSION_1_1, httpVersion);
        assertEquals("/index.html", httpRequest.getUrl());
        assertEquals(HttpMethod.GET, httpRequest.getMethod());
        assertNull(httpHeader.getContent_Type());
        assertEquals("no-cache", httpHeader.getCache_Control());
        assertEquals("localhost:5763", httpHeader.getHost());
        assertEquals("Keep-Alive", httpHeader.getConnection());
        assertEquals("Apache-HttpClient/4.5.5 (Java/1.8.0_152-release)", httpHeader.getUser_Agent());
        assertEquals("gzip,deflate", httpHeader.getAccept_Encoding());
        assertNull(httpRequest.getContent());
    }

    @Test
    public void splitTest() {
        String str = ",,";
        String[] strArr = str.split(",");
        assertArrayEquals(new String[]{}, strArr);
        str = ",,2";
        strArr = str.split(",");
        assertArrayEquals(new String[]{"", "", "2"}, strArr);
        str = "1,2,";
        strArr = str.split(",");
        assertArrayEquals(new String[]{"1", "2"}, strArr);
    }
}