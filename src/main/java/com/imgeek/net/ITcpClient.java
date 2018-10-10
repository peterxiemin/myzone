package com.imgeek.net;

import java.io.IOException;

/**
 * @author: peterxiemin
 *
 * @date: 2018/10/10 12:58
 * @desc:
 **/
public interface ITcpClient {
    void sendToServer() throws IOException, InterruptedException;
}
