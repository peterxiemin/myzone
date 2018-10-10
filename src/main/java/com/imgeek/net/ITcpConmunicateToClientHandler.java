package com.imgeek.net;

import java.io.IOException;
import java.net.Socket;

/**
 * @author: peterxiemin
 * @date: 2018/10/10 11:18
 * @desc:
 **/
public interface ITcpConmunicateToClientHandler {
    void handle(Socket socket) throws IOException;
}
