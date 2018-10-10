package com.imgeek.net;

import java.io.IOException;

/**
 * @author: peterxiemin
 * @date: 2018/10/10 11:25
 * @desc:
 **/
public interface ITcpServer {
    void createServerSocketAndWaitConnection() throws IOException;
}
