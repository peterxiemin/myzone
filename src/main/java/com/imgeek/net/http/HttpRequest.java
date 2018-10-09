package com.imgeek.net.http;

import com.imgeek.net.http.constants.HttpHeader;
import com.imgeek.net.http.constants.HttpMethod;
import com.imgeek.net.http.constants.HttpVersion;
import lombok.Data;
/**
 * @author :xiemin
 * @date:   2018-10-09
 */
@Data
public class HttpRequest {
    private HttpMethod method;
    private String url;
    private HttpVersion httpVersion;
    private HttpHeader httpHeader;
    private String content;
}
