package com.imgeek.net;

import java.io.IOException;
import java.net.Socket;

/**
 * @author: peterxiemin
 *
 * @date: 2018/10/10 12:53
 * @desc:
 **/
public interface ITcpConmunicateToServerHandler {
    void handle(Socket socket) throws IOException;
}
