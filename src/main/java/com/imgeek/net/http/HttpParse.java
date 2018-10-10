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
                }
                ret.append(String.format("%s: %s\r\n", key, entry.getValue()));
            }
        }
        ret.append("\r\n");
        ret.append(httpResponse.getContent());
        return ret.toString();
    }

    public static HttpRequest parse(String content) {
        HttpRequest httpRequest = new HttpRequest();
        String[] contentItem = content.split("(\r\n)+");
        try {
            String[] l1s = contentItem[0].split(" ");
            //set method
            switch (l1s[0]) {
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
            httpRequest.setUrl(l1s[1]);
            //set version
            switch (l1s[2]) {
                case HttpConstants.VERSION_1_1:
                    httpRequest.setHttpVersion(HttpVersion.v_1_1);
                    break;
                case HttpConstants.VERSION_1_0:
                    httpRequest.setHttpVersion(HttpVersion.v_1_0);
                    break;
                default:
                    throw new RuntimeException();
            }
            //set header
            HttpHeader httpHeader = new HttpHeader();
            int i = 1;
            for (; i < contentItem.length; i++) {
                String[] l2s = contentItem[i].split(": ");
                switch (l2s[0]) {
                    case "Accept":
                        httpHeader.setAccept(l2s[1]);
                        break;
                    case "Cache-Control":
                        httpHeader.setCache_Control(l2s[1]);
                        break;
                    case "Host":
                        httpHeader.setHost(l2s[1]);
                        break;
                    case "Connection":
                        httpHeader.setConnection(l2s[1]);
                        break;
                    case "User-Agent":
                        httpHeader.setUser_Agent(l2s[1]);
                        break;
                    case "Accept-Encoding":
                        httpHeader.setAccept_Encoding(l2s[1]);
                        break;
                    default:
                        throw new RuntimeException();
                }
            }
            httpRequest.setHttpHeader(httpHeader);
            //set content
            httpRequest.setContent(contentItem[i - 1]);
        } catch (RuntimeException e) {
            log.error(e.getMessage(), e);
            throw new HttpParseException("parse http protol error");
        }
        return httpRequest;
    }
}
