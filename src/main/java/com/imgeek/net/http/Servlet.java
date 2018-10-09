package com.imgeek.net.http;

import com.imgeek.net.io.MyFileIO;

import java.io.IOException;
import java.net.URL;
/**
 * @author :xiemin
 * @date:   2018-10-09
 */
public class Servlet {
    public void doGet(HttpRequest httpRequest, HttpResponse httpResponse) throws IOException {
        URL url = Servlet.class.getClassLoader().getResource("www".concat(httpRequest.getUrl()));
        StringBuffer fileContent = new StringBuffer();
        new MyFileIO(url.getFile(), (str) -> {
            fileContent.append(str);
        }, true);
        httpResponse.setContent(fileContent.toString());
    }
}
