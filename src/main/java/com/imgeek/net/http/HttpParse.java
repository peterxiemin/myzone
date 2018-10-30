package com.imgeek.net.http;

import com.alibaba.fastjson.JSONObject;
import com.imgeek.net.http.constants.HttpConstants;
import com.imgeek.net.http.constants.HttpHeader;
import com.imgeek.net.http.constants.HttpMethod;
import com.imgeek.net.http.constants.HttpVersion;
import com.imgeek.net.http.exceptions.HttpParseException;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * @author :xiemin
 * @date: 2018-10-09
 */
@Slf4j
public class HttpParse {
    public static String deParse(HttpResponse httpResponse) {
        //set status
        StringBuffer ret = new StringBuffer();
        String httpVersion = HttpConstants.VERSION_1_1;
        switch (httpResponse.getHttpVersion()) {
            case v_1_1:
                httpVersion = HttpConstants.VERSION_1_1;
                break;
            case v_1_0:
                httpVersion = HttpConstants.VERSION_1_0;
                break;
        }
        ret.append(String.format("%s %d %s", httpVersion, httpResponse.getStatus(), HttpConstants.OK_DESC));
        ret.append("\r\n");
        //set response header
        HttpHeader httpHeader = httpResponse.getHttpHeader();
        JSONObject obj = (JSONObject) JSONObject.toJSON(httpHeader);
        for (Map.Entry<String, Object> entry : obj.entrySet()) {
            if (entry.getValue() != null) {
                String key = "unknow";
                switch (entry.getKey()) {
                    case "content_Type":
                        key = HttpConstants.CONTENT_TYPE_KEY;
                        break;
                    case "content_Length":
                        key = HttpConstants.CONTENT_LENGTH_KEY;
                        break;
                    case "accept":
                        key = HttpConstants.ACCEPT_KEY;
                        break;
                }
                ret.append(String.format("%s: %s\r\n", key, entry.getValue()));
            }
        }
        ret.append("\r\n");
        //set content
        ret.append(httpResponse.getContent());
        return ret.toString();
    }

    public static HttpRequest parse(String content) {
        HttpRequest httpRequest = new HttpRequest();
        String[] methodHeaderAndBodyArr = content.split("\r\n\r\n");
        String methodHeader = methodHeaderAndBodyArr[0];
        String[] methodHeaderArr = methodHeader.split("\r\n");

        try {
            String methodPart = methodHeaderArr[0];
            methodHandle(httpRequest, methodPart.split(" "));
            String[] headPartArr = new String[methodHeaderArr.length - 1];
            System.arraycopy(methodHeaderArr, 1, headPartArr, 0, methodHeaderArr.length - 1);
            headerHandle(httpRequest, headPartArr);

            if (methodHeaderAndBodyArr.length > 1) {
                contentHandle(httpRequest, methodHeaderAndBodyArr[1]);
            }

        } catch (RuntimeException e) {
            log.error(e.getMessage(), e);
            throw new HttpParseException("parse http protol error");
        }
        return httpRequest;
    }

    private static void methodHandle(HttpRequest httpRequest, String[] strArr) {
        //set method
        switch (strArr[0]) {
            case "GET":
                httpRequest.setMethod(HttpMethod.GET);
                break;
            case "POST":
                httpRequest.setMethod(HttpMethod.POST);
                break;
            case "PUT":
                httpRequest.setMethod(HttpMethod.PUT);
                break;
            case "DELETE":
                httpRequest.setMethod(HttpMethod.DELETE);
                break;
            default:
                throw new RuntimeException();
        }
        //set url
        if (strArr[1].equalsIgnoreCase("/")) {
            httpRequest.setUrl("/index.html");
        } else {
            httpRequest.setUrl(strArr[1]);
        }
        //set version
        switch (strArr[2]) {
            case HttpConstants.VERSION_1_1:
                httpRequest.setHttpVersion(HttpVersion.v_1_1);
                break;
            case HttpConstants.VERSION_1_0:
                httpRequest.setHttpVersion(HttpVersion.v_1_0);
                break;
            default:
                throw new RuntimeException();
        }
    }

    private static void headerHandle(HttpRequest httpRequest, String[] strArr) {
//set header
        HttpHeader httpHeader = new HttpHeader();
        for (int i = 0; i < strArr.length; i++) {
            String[] lineArr = strArr[i].split(": ");
            switch (lineArr[0]) {
                case "Accept":
                    httpHeader.setAccept(lineArr[1]);
                    break;
                case "Cache-Control":
                    httpHeader.setCache_Control(lineArr[1]);
                    break;
                case "Host":
                    httpHeader.setHost(lineArr[1]);
                    break;
                case "Connection":
                    httpHeader.setConnection(lineArr[1]);
                    break;
                case "User-Agent":
                    httpHeader.setUser_Agent(lineArr[1]);
                    break;
                case "Accept-Encoding":
                    httpHeader.setAccept_Encoding(lineArr[1]);
                    break;
                default:
                    throw new RuntimeException();
            }
        }
        httpRequest.setHttpHeader(httpHeader);
    }

    private static void contentHandle(HttpRequest httpRequest, String body) {
        httpRequest.setContent(body);
    }
}
