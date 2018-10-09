package com.imgeek.net.http;

import com.imgeek.net.IProtocolHandler;
import com.imgeek.net.http.constants.HttpConstants;
import com.imgeek.net.http.constants.HttpHeader;
import com.imgeek.net.http.constants.HttpVersion;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class HttpProtocolHandler implements IProtocolHandler {
    /**
     * 根据获取的String字符串进行http协议相关内容解析
     *
     * @param recvData
     */
    @Override
    public String handle(String recvData) {
        HttpRequest httpRequest = HttpParse.parse(recvData);
        Servlet servlet = new Servlet();
        String responseData = null;
        switch (httpRequest.getMethod()) {
            case GET:
                HttpResponse httpResponse = new HttpResponse();
                httpResponse.setHttpVersion(HttpVersion.v_1_1);
                HttpHeader httpHeader = new HttpHeader();
                httpHeader.setContent_Type(HttpConstants.CONTENT_TYPE_VAL);
                try {
                    servlet.doGet(httpRequest, httpResponse);
                    httpResponse.setStatus(HttpConstants.OK_CODE);
                } catch (IOException e) {
                    log.error(e.getMessage(), e);
                    httpResponse.setStatus(HttpConstants.Not_Found_CODE);
                    httpResponse.setContent(HttpConstants.Not_Found_DESC);
                } catch (Exception e) {
                    log.error(e.getMessage(), e);
                    httpResponse.setStatus(HttpConstants.Internal_Server_Error_CODE);
                    httpResponse.setContent(HttpConstants.Internal_Server_Error_DESC);
                }
                httpHeader.setContent_Length(httpResponse.getContent().length());
                httpResponse.setHttpHeader(httpHeader);
                responseData = HttpParse.deParse(httpResponse);
                break;
            case POST:
                break;
        }
        return responseData;
    }
}
