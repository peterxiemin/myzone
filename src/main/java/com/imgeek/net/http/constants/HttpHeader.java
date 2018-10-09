package com.imgeek.net.http.constants;

import lombok.Data;
/**
 * @author :xiemin
 * @date:   2018-10-09
 */
@Data
public class HttpHeader {
    private String Accept;
    private String Cache_Control;
    private String Host;
    private String Connection;
    private String User_Agent;
    private String Accept_Encoding;
    private int Content_Length;
    private String Content_Type;
}
